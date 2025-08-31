package com.bhavya.foodorder.screens.HistoryScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bhavya.foodorder.ViewModel.CartViewModel
import com.bhavya.foodorder.ViewModel.HistoryViewmodel
import com.bhavya.foodorder.ViewModel.ProfileViewModel
import com.bhavya.foodorder.screens.HomeScreen.LightGrayCustom
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(navController: NavController,cartViewModel: CartViewModel,profileViewModel: ProfileViewModel,historyViewmodel: HistoryViewmodel) {
    val historyList by historyViewmodel.historyItems.collectAsState(initial = emptyList())
    val profilee=profileViewModel.profile

    androidx.compose.material.Surface(
        modifier = Modifier.fillMaxSize().background(color = LightGrayCustom)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Content Column
            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp, 50.dp, 20.dp, 100.dp)
                    .verticalScroll(rememberScrollState()),
                // Bottom padding for button space
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "",
                        modifier = Modifier.size(35.dp).clickable {
                            navController.navigate("home")
                        }
                    )
                    Text(
                        text = "History",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(start = 90.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
if (historyList.isEmpty()) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            imageVector = Icons.Default.History,
            contentDescription = "",
            modifier = Modifier.size(250.dp),
            colorFilter = ColorFilter.tint(Color.LightGray)

        )
        Text(
            text = "No History Yet",
            fontSize = 42.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Hit the orange button down below to Create an order",
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(200.dp))

        Button(
            onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFA4A0C)
            ),
            modifier = Modifier.fillMaxWidth().height(55.dp),
            shape = RoundedCornerShape(20.dp)
        ) {

            Text(text = "Place An Order", fontSize = 20.sp)
        }
    }
}else
    Column( modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text="All Orders Placed", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(50.dp))
       historyList.forEach {foodItems ->
      (      Card(modifier = Modifier.fillMaxWidth().height(273.dp).padding(10.dp), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(Color(0xFF7DF9FF))) {
Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
    Text(text="User Info", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)

    Text(text="Name= ${profilee.value.Name}", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
    Text(text="Address= ${profilee.value.Address}", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
    Text(text="MobileNo. =${profilee.value.MObileNO}", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
Spacer(modifier = Modifier.height(20.dp))
    Text(text="Food Order Info", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)

    Text(text="Food Id= ${foodItems.foodId}", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
    Text(text="Food Name= ${foodItems.foodName}", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
    Text(text="Price= ${foodItems.price}", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
    Text(
        text = foodItems.dateTime,
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.SemiBold
    )
}
            })

        }

    }


            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentDateTime(): String {
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
    return current.format(formatter)
}