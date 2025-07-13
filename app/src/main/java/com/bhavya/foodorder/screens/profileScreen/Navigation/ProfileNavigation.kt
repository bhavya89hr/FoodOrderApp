package com.bhavya.foodorder.screens.profileScreen.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bhavya.foodorder.screens.profileScreen.profileEdit
import com.bhavya.foodorder.screens.profileScreen.profileScreen


@Composable
    fun ProfileNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = profileNav.ProfileScreen.name ) {
        composable(route = profileNav.ProfileScreen.name) { backStackEntry ->
            val savedStateHandle = backStackEntry.savedStateHandle
            val name = savedStateHandle.get<String>("name") ?: "Prince"
            val email = savedStateHandle.get<String>("email") ?: "PrinceBeniwal@gmail.com"
            val mobile = savedStateHandle.get<String>("mobile") ?: "+8973874962"
            val Address= savedStateHandle.get<String>("Address") ?:"Sonipat,Harayana"

            profileScreen(navController, name, email, mobile, Address   )
        }
        val profileRoute=profileNav.ProfileEdit.name
        composable(route= "$profileRoute/{name}/{email}/{mobileNo}/{Address}",
            arguments = listOf(navArgument("email") { type= NavType.StringType },
                navArgument("name") { type= NavType.StringType },
                        navArgument("mobileNo") { type= NavType.StringType },
                navArgument("Address") { type= NavType.StringType }
              )) {
            val  email = it.arguments?.getString("email")
            val name = it.arguments?.getString("name")
            val Address = it.arguments?.getString("Address")
            val mobileNo = it.arguments?.getString("mobileNo")
            profileEdit(email,name,mobileNo,Address,navController)

        }

    }
}
