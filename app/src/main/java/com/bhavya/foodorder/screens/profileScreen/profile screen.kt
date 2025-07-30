package com.bhavya.foodorder.screens.profileScreen
import com.bhavya.foodorder.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bhavya.foodorder.ViewModel.ProfileViewModel


@Composable
fun profileScreen(navController: NavController,profileViewModel: ProfileViewModel) {

val name= profileViewModel.profile.value
    Surface(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

        Column(
            modifier = Modifier.fillMaxSize().background(color = Color.LightGray)
                .padding(30.dp, 20.dp),

            horizontalAlignment = Alignment.Start
        ) {
            val options = remember {
                listOf("orders", "pending reviews", "Faq", "Help")
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .clickable{navController.navigate("home")}
                    .padding(0.dp, 50.dp)

                    .size(30.dp)
            )
            Text(
                text = "My Profile",
                color = Color.Black,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(0.dp, 10.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "personal details", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = "change",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    modifier = Modifier.clickable {
                        navController.navigate("Edit")
                    })

            }
            Card(
                modifier = Modifier
                    .height(200.dp).fillMaxWidth().padding(0.dp, 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.elevatedCardElevation(3.dp)
            ) {
                Row {
                    Box(modifier = Modifier.height(125.dp).width(125.dp).padding(15.dp).clip(RoundedCornerShape(10.dp))) {
                        Image(
                            painter = painterResource(id = R.drawable.imgg),
                            contentDescription = "Default Profile Picture",
                            modifier = Modifier
                                .fillMaxSize()


                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))

                    Column(modifier = Modifier.fillMaxHeight().width(205.dp).padding(0.dp, 20.dp).verticalScroll(rememberScrollState())) {
                        Text(text = name.name, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                        Divider(modifier = Modifier.padding(5.dp))
                        Text(text = name.email, fontSize = 20.sp)
                        Divider(modifier = Modifier.padding(5.dp))
                        Text(text =name.MobileNO, fontSize = 20.sp)
                        Divider(modifier = Modifier.padding(5.dp))
                        Text(text = name.Adrees, fontSize = 20.sp)
                    }
                }
            }

            Column {
              options.forEach {
                  text->
                  var selectedItem by remember { mutableStateOf("") }
                 if(text==selectedItem){
                     when(text){
                         "orders"->navController.navigate("Cart")
                         "Faq"->navController.navigate("Fav")
                     }

                 }
                  Card(modifier = Modifier.height(80.dp).fillMaxWidth().padding(0.dp,10.dp), colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(20.dp)) {
           Row(modifier = Modifier.fillMaxSize().padding(15.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                   Text(text = text, fontSize = 22.sp, fontWeight = FontWeight.W500,modifier = Modifier.padding(10.dp,0.dp))
               Icon(
                   imageVector = Icons.Default.KeyboardArrowRight,
                   contentDescription = null,
                   tint = Color.Black,
                   modifier = Modifier
                       .clickable{selectedItem=text}
                       .size(30.dp)
               )
           }
                  }
              }
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color.Red), modifier = Modifier.fillMaxWidth().height(55.dp), shape =  RoundedCornerShape(20.dp)) {
                    Text(text = "Update", fontSize = 20.sp)
                }
            }
        }


    }

}