package com.bhavya.foodorder.screens.DrawerScreeen

import android.graphics.drawable.Icon
import android.view.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class DrawerItems(val title: String,val icon: ImageVector) {

    object profile: DrawerItems("Profile", Icons.Default.Person)
    object Orders : DrawerItems("Orders", Icons.Default.ShoppingCart)
    object Offers : DrawerItems("Offer and Promo", Icons.Default.Star)
    object Privacy : DrawerItems("Privacy Policy", Icons.Default.AccountBox)
    object Security : DrawerItems("Security", Icons.Default.Lock)
    object SignOut : DrawerItems("Sign-out", Icons.Default.ExitToApp)
}

@Composable
fun DrawerFun(onItemClick: (DrawerItems) -> Unit ){
    val items=listOf(
        DrawerItems.profile,
        DrawerItems.Orders,
        DrawerItems.Offers,
                DrawerItems.Security,
                DrawerItems.Privacy,
                DrawerItems.SignOut
    )
androidx.compose.material3.Surface(modifier = Modifier.fillMaxHeight().width(300.dp) ,color = Color(0xFFFF4500)) {

        Column(modifier = Modifier.fillMaxSize().padding(30.dp,150.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceEvenly){
            items.forEach {items->
            Row(modifier = Modifier.fillMaxWidth().height(30.dp).clickable{}, verticalAlignment = Alignment.CenterVertically){
                Image(imageVector = items.icon, contentDescription = "", modifier = Modifier.size(50.dp), colorFilter = ColorFilter.tint(color = Color.White)  )

//                Spacer(modifier = Modifier.width(30.dp))
                Text(text = items.title, fontSize = 25.sp, color = Color.White)

            }
                Divider(modifier = Modifier.width(200.dp))
        }

    }
}
}
