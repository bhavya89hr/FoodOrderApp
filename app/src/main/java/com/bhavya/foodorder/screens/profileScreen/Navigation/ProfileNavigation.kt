package com.bhavya.foodorder.screens.profileScreen.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bhavya.foodorder.screens.profileScreen.profileEdit
import com.bhavya.foodorder.screens.profileScreen.profileScreen


@Composable
    fun ProfileNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = profileNav.ProfileScreen.name ) {
composable(route= profileNav.ProfileScreen.name) {
    profileScreen(navController)
}
        composable(route= profileNav.ProfileEdit.name) {
            profileEdit(navController)
        }

    }
}
