package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receiptlist

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kursor.roombookkeepingmobileupstack.features.R
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Receipt
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.special.ListItemLayout
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.special.RoomBookkeepingTopAppBar
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReceiptListLayout(
    component: ReceiptListComponent,
    modifier: Modifier = Modifier
) {

    val receiptList = component.receiptListState
    val selectedReceipts = component.selectedReceiptsState

    Scaffold(
        floatingActionButton = {
            Button(onClick = {
                component.onAddReceiptButtonClick()
            }) {
                Text(text = stringResource(id = R.string.add_receipt))
            }
        },
        topBar = {
            RoomBookkeepingTopAppBar {
                if (selectedReceipts.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            component.deleteSelectedReceipts()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "DeleteReceipts"
                        )
                    }
                }
                IconButton(
                    onClick = {
                        component.onPersonButtonClicked()
                    }) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "PersonList")
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            itemsIndexed(receiptList) { index, receipt ->
                ListItemLayout(index = index) {
                    SelectableReceiptListItemLayout(
                        receipt = receipt,
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        component.changeSelectionForReceipt(receipt)
                                    },
                                    onTap = {
                                        if (selectedReceipts.isEmpty())
                                            component.onReceiptClick(receipt.id)
                                        else component.changeSelectionForReceipt(receipt)
                                    }
                                )
                            },
                        selectionCriteria = { receipt in selectedReceipts }
                    )
                }
            }
        }
    }
}

@Composable
fun SelectableReceiptListItemLayout(
    receipt: Receipt,
    modifier: Modifier = Modifier,
    selectionCriteria: () -> Boolean
) {
    if (selectionCriteria()) SelectedReceiptListItemLayout(receipt = receipt, modifier = modifier)
    else UnselectedReceiptListItemLayout(receipt = receipt, modifier = modifier)
}


@Composable
fun UnselectedReceiptListItemLayout(
    receipt: Receipt,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified
) {
    Row(modifier = modifier) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .padding(
                    horizontal = 12.dp,
                    vertical = 4.dp
                )
        ) {
            Text(
                text = SimpleDateFormat(
                    "HH:mm",
                    Locale.getDefault()
                ).format(receipt.dateTime),
                style = TextStyle(
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Center,
                color = textColor,
                modifier = Modifier.fillMaxSize()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = SimpleDateFormat(
                    "dd.MM.yyyy",
                    Locale.getDefault()
                ).format(receipt.dateTime),
                style = TextStyle(
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Center,
                color = textColor,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = receipt.priceList.sumOf { it.value }.toString(),
                style = TextStyle(
                    fontSize = 22.sp
                ),
                color = textColor,
                textAlign = TextAlign.Center
            )
            Text(
                text = receipt.name,
                color = textColor,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }


}

@Composable
fun SelectedReceiptListItemLayout(
    receipt: Receipt,
    modifier: Modifier = Modifier
) {
    Surface(color = MaterialTheme.colors.surface) {
        UnselectedReceiptListItemLayout(
            receipt = receipt,
            modifier = modifier,
            textColor = MaterialTheme.colors.primary
        )
    }
}