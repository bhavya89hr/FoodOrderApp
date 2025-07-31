package com.bhavya.foodorder.screens.DeliveryScreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bhavya.foodorder.R
import com.bhavya.foodorder.ViewModel.CartViewModel
import com.bhavya.foodorder.ViewModel.ProfileViewModel
import com.bhavya.foodorder.screens.paymentScreen.PaymentScreen


@Composable
fun DeliveryScreen(profileViewModel: ProfileViewModel,navController: NavController,cartViewModel: CartViewModel) {
    val name = profileViewModel.profile.value
    val cartItem = cartViewModel.cartItems
    var newSelection by remember { mutableStateOf("") }
    if (newSelection=="") {

        val sum = cartItem.sumOf { it.price }
        Surface(
            modifier = Modifier.fillMaxSize().background(Color.LightGray)

        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(30.dp),
                horizontalAlignment = Alignment.Start
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
                        modifier = Modifier.size(30.dp).clickable {
                            navController.navigate("Cart")
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
                    text = "Delivery",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 35.sp,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "address details", fontSize = 20.sp, fontWeight = FontWeight.Normal)
                    Text(
                        text = "Fill Your Details",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Red,
                        modifier = Modifier.clickable {
                            navController.navigate("Edit")
                        })

                }
                Spacer(modifier = Modifier.height(40.dp))

                Card(
                    modifier = Modifier.fillMaxWidth().height(160.dp).clickable{navController.navigate("Edit")},
                    elevation = 6.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(20.dp, 10.dp),
                        verticalArrangement = Arrangement.SpaceEvenly

                    ) {
                        Text(text = name.name, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                        Divider(modifier = Modifier.padding(5.dp))
                        Text(text = name.Adrees, fontSize = 20.sp)
                        Divider(modifier = Modifier.padding(5.dp))
                        Text(text = name.MobileNO, fontSize = 20.sp)


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
                var selectedMethod by remember { mutableStateOf("Door delivery") }
                Card(
                    modifier = Modifier.fillMaxWidth().height(160.dp),
                    elevation = 6.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
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
                            RadioButton(
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
                        newSelection = selectedMethod
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


    }else
        newSelection.let { value->
        PaymentScreen(
            value=value,
            navController = navController, profileViewModel = profileViewModel,
            cartViewModel = cartViewModel
        )
        return
    }
}