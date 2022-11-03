package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.person

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.special.RoomBookkeepingTopAppBar

@Composable
fun PersonLayout(
    navController: NavController,
    personId: Long,
    personViewModel: PersonViewModel = getViewModel<PersonViewModel>().also {
        it.loadData(personId)
    }
) {

    val name = personViewModel.nameLiveData.observeAsState(initial = "")

    Scaffold(
        topBar = { RoomBookkeepingTopAppBar(navController = navController) }
    ) {
        Column(
            modifier = Modifier.width(IntrinsicSize.Max)
        ) {
            OutlinedTextField(
                value = name.value,
                onValueChange = personViewModel::changeName,
                placeholder = {
                    Text(text = stringResource(id = R.string.name_animate))
                }
            )
            Button(
                onClick = {
                    personViewModel.submit()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.submit))
            }
        }
    }
}
