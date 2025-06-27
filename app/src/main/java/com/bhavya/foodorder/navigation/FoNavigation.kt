package com.bhavya.foodorder.navigation

import android.appwidget.AppWidgetProvider
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bhavya.foodorder.screens.GetStarted
import com.bhavya.foodorder.screens.HomeScreen.HomeScreen
import com.bhavya.foodorder.screens.LoginScreen.LoginScreen

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
    }

}

