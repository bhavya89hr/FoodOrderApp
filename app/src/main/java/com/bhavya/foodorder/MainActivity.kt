package com.bhavya.foodorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bhavya.foodorder.navigation.FoNavigation
import com.bhavya.foodorder.screens.GetStarted
import com.bhavya.foodorder.screens.HomeScreen.HomeScreen
import com.bhavya.foodorder.screens.profileScreen.Navigation.ProfileNav
import com.bhavya.foodorder.screens.profileScreen.profileScreen

import com.bhavya.foodorder.ui.theme.FoodOrderTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)

        setContent {
            FoodOrderTheme {

            FoNavigation()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodOrderTheme {
        Greeting("Android")
    }
}