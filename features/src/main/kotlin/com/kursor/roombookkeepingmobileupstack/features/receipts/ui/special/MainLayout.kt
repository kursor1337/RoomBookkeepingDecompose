package com.kursor.roombookkeepingmobileupstack.features.receipts.ui.special

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.Layouts
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.layouts.SplashScreenLayout
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.person.PersonLayout
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.person.PersonListLayout
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.price.PriceLayout
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receipt.ReceiptLayout
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.receiptlist.ReceiptListLayout
import com.kursor.roombookkeepingmobileupstack.features.receipts.ui.special.dontGoHere.NothingImportantLayout

@Composable
fun MainLayout() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Layouts.ReceiptListLayout.route) {

        composable(Layouts.SplashLayout.route) {
            SplashScreenLayout()
        }

        composable(
            route = Layouts.ReceiptListLayout.route
        ) {
            ReceiptListLayout(
                navController = navController
            )
        }

        composable(
            route = Layouts.ReceiptLayout.route,
            arguments = listOf(
                navArgument(Layouts.Args.RECEIPT_ID) { type = NavType.LongType }
            )
        ) {
            val receiptId = it.arguments?.getLong(Layouts.Args.RECEIPT_ID) ?: -1
            ReceiptLayout(
                navController = navController,
                receiptId = receiptId
            )
        }

        composable(
            route = Layouts.PriceLayout.route,
            arguments = listOf(
                navArgument(Layouts.Args.RECEIPT_ID) { type = NavType.LongType },
                navArgument(Layouts.Args.PRICE_INDEX) { type = NavType.IntType }
            )
        ) {
            val receiptId = it.arguments!!.getLong(Layouts.Args.RECEIPT_ID)
            val priceIndex = it.arguments?.getInt(Layouts.Args.PRICE_INDEX) ?: -1
            PriceLayout(
                navController = navController,
                receiptId = receiptId,
                priceIndex = priceIndex
            )
        }

        composable(
            route = Layouts.PersonListLayout.route
        ) {
            PersonListLayout(
                navController = navController
            )
        }

        composable(
            route = Layouts.PersonLayout.route,
            arguments = listOf(
                navArgument(Layouts.Args.PERSON_ID) { type = NavType.LongType }
            )
        ) {
            val personId = it.arguments?.getLong(Layouts.Args.PERSON_ID) ?: -1
            PersonLayout(
                navController = navController,
                personId = personId
            )
        }
        
        composable(
            route = "nothingImportantHere"
        ) {
            NothingImportantLayout(navController = navController)
        }

    }
}