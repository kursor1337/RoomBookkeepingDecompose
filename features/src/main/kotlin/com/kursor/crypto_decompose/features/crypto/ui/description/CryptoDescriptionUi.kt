package com.kursor.crypto_decompose.features.crypto.ui.description

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.kursor.crypto_decompose.core.widget.SwipeRefreshLceWidget

@Composable
fun CryptoDescriptionUi(
    component: CryptoDescriptionComponent,
    modifier: Modifier = Modifier
) {

    val cryptoDescription = component.cryptoDescriptionState

    val connectionStatus =
        viewModel.connectionStatusLiveData.observeAsState(ConnectionStatus.LOADING)

    viewModel.loadData(cryptoId)

    Scaffold(
        topBar = {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        modifier = Modifier.padding(6.dp),
                        onClick = { component.onBackButtonPressed() }
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                    Text(
                        text = cryptoDescription.name,
                        modifier = Modifier.padding(
                            vertical = 12.dp,
                            horizontal = 12.dp
                        ),
                        style = TextStyle(
                            fontSize = 22.sp
                        ),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Divider(
                    modifier = Modifier.height(3.dp)
                )
            }
        }
    ) {
        SwipeRefreshLceWidget(
            state = component.cryptoDescriptionState,
            onRefresh = component::refresh,
            onRetryClick = component::refresh
        ) { cryptoDescription, refreshing ->
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(it)
            ) {
                item {
                    Image(
                        painter = rememberAsyncImagePainter(cryptoImageLink),
                        contentDescription = "crypto icon",
                        modifier = Modifier
                            .padding(12.dp)
                            .size(120.dp)
                    )

                    TextBlock(
                        title = stringResource(id = R.string.crypto_currency_description),
                        text = cryptoDescription.value?.description?.en ?: "",
                        modifier = Modifier.padding(6.dp)
                    )

                    TextBlock(
                        title = stringResource(id = R.string.crypto_currency_categories),
                        text = cryptoDescription.value?.categories?.joinToString()
                            ?: "",
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TextBlock(
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 4.dp,
                    horizontal = 12.dp
                ),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 22.sp
            )
        )
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 4.dp,
                    horizontal = 12.dp
                ),
            textAlign = TextAlign.Start,
        )
    }
}