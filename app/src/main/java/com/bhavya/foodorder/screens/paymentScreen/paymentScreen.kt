package com.bhavya.foodorder.screens.paymentScreen

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
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bhavya.foodorder.HistoryRoom.HistoryEntity
import com.bhavya.foodorder.ViewModel.CartViewModel
import com.bhavya.foodorder.ViewModel.HistoryViewmodel
import com.bhavya.foodorder.ViewModel.ProfileViewModel
import com.bhavya.foodorder.screens.HistoryScreen.getCurrentDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentScreen(profileViewModel: ProfileViewModel,navController: NavController,cartViewModel: CartViewModel, value: String,historyViewmodel: HistoryViewmodel){
    val name= profileViewModel.profile.value
    val cartItem=cartViewModel.cartItems


  val sum=cartItem.sumOf{it.price}

    Surface(modifier = Modifier.fillMaxSize().background(Color.LightGray).verticalScroll(
        rememberScrollState()
    )

    ) {
        Column(modifier = Modifier.fillMaxSize().padding(30.dp), horizontalAlignment = Alignment.Start){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp).clickable {
                        navController.navigate("Delivery")
                    }
                )
                Text(
                    text = "Checkout",
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 110.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Payment",
                fontWeight = FontWeight.SemiBold,
                fontSize = 35.sp,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "address method", fontSize = 20.sp, fontWeight = FontWeight.Normal)


            }
            Spacer(modifier = Modifier.height(40.dp))
            var selectedMetho by remember { mutableStateOf("Card") }

            Card(modifier = Modifier.fillMaxWidth().height(160.dp), elevation = 6.dp, shape = RoundedCornerShape(10.dp)) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp,10.dp), verticalArrangement = Arrangement.SpaceEvenly

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { selectedMetho = "Card" }
                    ) {
                        androidx.compose.material.RadioButton(
                            selected = selectedMetho == "Card",
                            onClick = { selectedMetho = "Card" }
                        )
                        Card(modifier = Modifier.size(30.dp), shape = RoundedCornerShape(7.dp)) {
                            Image(imageVector = Icons.Default.CreditCard, contentDescription = "")
                        }
                        Text(
                            text = "Card",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Divider(modifier = Modifier.padding(5.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { selectedMetho = "Bank Account" }
                    ) {
                        androidx.compose.material.RadioButton(
                            selected = selectedMetho == "Bank Account",
                            onClick = { selectedMetho = "Bank Account" }
                        )
                        Card(modifier = Modifier.size(30.dp), shape = RoundedCornerShape(7.dp)) {
                            Image(imageVector = Icons.Default.FoodBank, contentDescription = "")
                        }
                        Text(
                            text = "Bank Account",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Delivery Method",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(30.dp))
            var selectedMethod by remember { mutableStateOf(value) }
            Card(modifier = Modifier.fillMaxWidth().height(160.dp), elevation = 6.dp, shape = RoundedCornerShape(10.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp, 10.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { selectedMethod = "Door delivery" }
                    ) {
                        androidx.compose.material.RadioButton(
                            selected = selectedMethod == "Door delivery",
                            onClick = { selectedMethod = "Door delivery" }
                        )
                        Text(
                            text = "Door delivery",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Divider(modifier = Modifier.padding(5.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { selectedMethod = "PickUp" }
                    ) {
                        RadioButton(
                            selected = selectedMethod == "PickUp",
                            onClick = { selectedMethod = "PickUp" }
                        )
                        Text(
                            text = "PickUp",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total ", fontSize = 20.sp, fontWeight = FontWeight.Normal)

                Text(
                    text = sum.toString(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.clickable {
//                        navController.navigate("Edit")
                    })

            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    val profile = profileViewModel.profile.value
                    val dateTime = getCurrentDateTime()

                    cartViewModel.cartItems.forEach { item ->
                        val history = HistoryEntity(
                            userName = profile.Name,
                            address = profile.Address,
                            mobileNo = profile.MObileNO,
                            foodId = item.id.toString(),
                            foodName = item.name,
                            price = item.price as Int,
                            dateTime = dateTime,
                            photoUrl = item.imageUrl
                        )
                        historyViewmodel.insertHistory(history)
                    }
                    cartViewModel.clearCart()
                    navController.navigate("History")
                          },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFA4A0C)
                ),
                modifier = Modifier.fillMaxWidth().height(55.dp),
                shape = RoundedCornerShape(20.dp)
            ) {

                Text(text = "Process to Payment", fontSize = 20.sp)
            }
        }
    }
}