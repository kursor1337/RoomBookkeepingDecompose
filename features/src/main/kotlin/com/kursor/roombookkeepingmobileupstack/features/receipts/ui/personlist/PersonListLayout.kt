package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.person

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kursor.roombookkeepingmobileupstack.features.receipts.domain.Person
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.Layouts
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.special.ListItemLayout
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.special.RoomBookkeepingTopAppBar

@Composable
fun PersonListLayout(
    navController: NavController,
    personListViewModel: PersonListViewModel = getViewModel<PersonListViewModel>().also {
        it.loadData()
    },
) {

    val personList = personListViewModel.personListLiveData.observeAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            Button(onClick = {
                navController.navigate(Layouts.PersonLayout.withArgs(-1))
            }) {
                Text(text = stringResource(id = R.string.add_person))
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        topBar = { RoomBookkeepingTopAppBar(navController = navController) }
    ) {
        LazyColumn {
            itemsIndexed(personList.value) { index, person ->
                ListItemLayout(index = index) {
                    PersonListItemLayout(
                        person = person,
                        modifier = Modifier.clickable {
                            navController.navigate(Layouts.PersonLayout.withArgs(person.id))
                        }.fillMaxWidth()
                    )
                }

            }
        }
    }
}


@Composable
fun PersonListItemLayout(
    person: Person,
    modifier: Modifier = Modifier
) {
    Text(
        text = person.name,
        style = TextStyle(
            fontSize = 22.sp
        ),
        modifier = modifier.padding(12.dp)
    )
}
