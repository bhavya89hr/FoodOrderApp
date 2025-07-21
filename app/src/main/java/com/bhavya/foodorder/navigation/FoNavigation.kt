package com.bhavya.foodorder.navigation

import FoodDetailScreen
import android.appwidget.AppWidgetProvider
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bhavya.foodorder.screens.CartScreen.CartScreen
import com.bhavya.foodorder.screens.ChatBotScreen.ChatBotScreen
import com.bhavya.foodorder.screens.GetStarted
import com.bhavya.foodorder.screens.HomeScreen.HomeScreen
import com.bhavya.foodorder.screens.LoginScreen.LoginScreen
import com.bhavya.foodorder.screens.profileScreen.Navigation.profileNav
import com.bhavya.foodorder.screens.profileScreen.profileEdit
import com.bhavya.foodorder.screens.profileScreen.profileScreen


@Composable
fun FoNavigation(){
    val navController= rememberNavController()
    NavHost(navController=navController, startDestination = AppScreens.HomeScreen.route){

        composable (AppScreens.GetStarted.route){
            GetStarted(navController)
        }

        composable (AppScreens.LoginScreen.route){
            LoginScreen( navController)
        }
        composable (AppScreens.HomeScreen.route){
            HomeScreen(
                navController=navController)
        }
        composable(AppScreens.CartScreen.route) {
            CartScreen(navController)
        }
        composable(AppScreens.ChatBotScreen.route) {
            ChatBotScreen(navController=navController)
        }
        composable(
            route = "detail/{name}/{price}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("price") { type = NavType.StringType } // You can also use DoubleType if passing double properly
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

            profileScreen(navController, name, email, mobile, address  )
        }
        val profileRoute=profileNav.ProfileEdit.name
        composable(route= "$profileRoute/{name}/{email}/{mobileNo}/{address}",
            arguments = listOf(navArgument("email") { type= NavType.StringType },
                navArgument("name") { type= NavType.StringType },
                navArgument("mobileNo") { type= NavType.StringType },
                navArgument("address") { type= NavType.StringType }
            )) {
            val  email = it.arguments?.getString("email")
            val name = it.arguments?.getString("name")
            val address = it.arguments?.getString("address")
            val mobileNo = it.arguments?.getString("mobileNo")
            profileEdit(email,name,mobileNo,address,navController)

        }


    }

}

