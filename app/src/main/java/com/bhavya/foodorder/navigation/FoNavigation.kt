package com.bhavya.foodorder.navigation

import FoodDetailScreen
import android.annotation.SuppressLint
import android.appwidget.AppWidgetProvider
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bhavya.foodorder.DataManager
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.bhavya.foodorder.ViewModel.CartViewModel
import com.bhavya.foodorder.ViewModel.FavouriteViewModel
import com.bhavya.foodorder.ViewModel.SharedSearchViewModel
import com.bhavya.foodorder.ViewModel.ProfileViewModel
import com.bhavya.foodorder.screens.CartScreen.CartScreen
import com.bhavya.foodorder.screens.CartScreen.FavouriteScreen
import com.bhavya.foodorder.screens.ChatBotScreen.ChatBotScreen
import com.bhavya.foodorder.screens.DeliveryScreen.DeliveryScreen
import com.bhavya.foodorder.screens.GetStarted
import com.bhavya.foodorder.screens.HistoryScreen.HistoryScreen
import com.bhavya.foodorder.screens.HomeScreen.CardDetail
import com.bhavya.foodorder.screens.HomeScreen.HomeScreen
import com.bhavya.foodorder.screens.LoginScreen.LoginScreen
import com.bhavya.foodorder.screens.SearchScreen.SearchResultScreen

import com.bhavya.foodorder.screens.profileScreen.profileEdit
import com.bhavya.foodorder.screens.profileScreen.profileScreen


@Composable
fun FoNavigation() {

    val navController = rememberNavController()
    val favouriteViewModel: FavouriteViewModel = viewModel()
    val profileViewModel: ProfileViewModel = viewModel()
    val cartviewmodel: CartViewModel = viewModel()
    val Searchitems: SharedSearchViewModel = viewModel()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(AppScreens.GetStarted.route) {
            GetStarted(navController)
        }

        composable(AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(AppScreens.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                cartViewModel = cartviewmodel,
                searchViewModel = Searchitems,
                favouriteViewModel = favouriteViewModel
            )
        }
        val CartScrreenRoute = AppScreens.CartScreen.route
        composable(CartScrreenRoute) {
            CartScreen(
                navController,
                cartViewModel = cartviewmodel,
                favouriteViewModel = favouriteViewModel,
                searchViewModel = Searchitems
            )
        }
        composable(AppScreens.ChatBotScreen.route) {
            ChatBotScreen(navController = navController)
        }
        composable(
            route = "detail/{name}/{price}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("price") {
                    type = NavType.StringType
                } // You can also use DoubleType if passing double properly
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val price = backStackEntry.arguments?.getString("price")?.toDoubleOrNull() ?: 0.0
            FoodDetailScreen(name = name, price = price)
        }
        composable(AppScreens.ProfileScreen.route) { backStackEntry ->
            val savedStateHandle = backStackEntry.savedStateHandle
            val name = savedStateHandle.get<String>("name") ?: "Prince"
            val email = savedStateHandle.get<String>("email") ?: "PrinceBeniwal@gmail.com"
            val mobile = savedStateHandle.get<String>("mobile") ?: "+8973874962"
            val address= savedStateHandle.get<String>("address") ?:"Sonipat,Harayana"

            profileScreen(
                navController = navController,
                profileViewModel = profileViewModel
            )
        }
        val profileRoute = AppScreens.ProfileEdit.route
        composable(
            route = profileRoute
//            arguments = listOf(navArgument("email") { type= NavType.StringType },
//                navArgument("name") { type= NavType.StringType },
//                navArgument("mobileNo") { type= NavType.StringType },
//                navArgument("address") { type= NavType.StringType }
        ) {
//            val email = it.arguments?.getString("email")
//            val name = it.arguments?.getString("name")
//            val address = it.arguments?.getString("address")
//            val mobileNo = it.arguments?.getString("mobileNo")
//
            profileEdit(
                navController,
                profileViewModel = profileViewModel
            )

        }
        composable(AppScreens.SearchScreen.route) {

            SearchResultScreen(
                navController = navController,
                query = "",
                searchViewModel = Searchitems,
                cartviewmodel,
                favouriteViewModel
            )

        }
        composable(AppScreens.FavouriteScreen.route) {
            FavouriteScreen(
                navController = navController,
                favouriteViewModel = favouriteViewModel,
                cartViewModel = cartviewmodel,
                searchViewModel = Searchitems
            )
        }
        composable(AppScreens.HistoryScreen.route) {
            HistoryScreen(navController)
        }
        composable(AppScreens.DeliveryScreen.route) {
   DeliveryScreen(profileViewModel, navController = navController, cartViewModel = cartviewmodel)
        }
    }
}




