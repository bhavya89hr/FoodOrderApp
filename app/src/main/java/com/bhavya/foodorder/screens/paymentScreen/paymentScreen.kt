package com.bhavya.foodorder.screens.paymentScreen

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bhavya.foodorder.HistoryRoom.HistoryEntity
import com.bhavya.foodorder.ViewModel.CartViewModel
import com.bhavya.foodorder.ViewModel.HistoryViewmodel
import com.bhavya.foodorder.ViewModel.ProfileViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentScreen(
    profileViewModel: ProfileViewModel,
    navController: NavController,
    cartViewModel: CartViewModel,
    historyViewmodel: HistoryViewmodel,
    value: String
) {
    val cartItems = cartViewModel.cartItems
    val sum = cartItems.sumOf { it.price }
    val context = LocalContext.current

    var selectedPayment by remember { mutableStateOf("Card") }
    var selectedDelivery by remember { mutableStateOf(value) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable { navController.navigate("Delivery") }
                )
                Text(
                    text = "Checkout",
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 110.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Payment",
                fontSize = 35.sp,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Payment Method", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(20.dp))

            // Payment Methods Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
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
                        modifier = Modifier.clickable { selectedPayment = "Card" }
                    ) {
                        RadioButton(
                            selected = selectedPayment == "Card",
                            onClick = { selectedPayment = "Card" }
                        )
                        Card(
                            modifier = Modifier.size(30.dp),
                            shape = RoundedCornerShape(7.dp)
                        ) {
                            Image(
                                imageVector = Icons.Default.CreditCard,
                                contentDescription = ""
                            )
                        }
                        Text(text = "Card", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                    }

                    Divider(modifier = Modifier.padding(5.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { selectedPayment = "Bank Account" }
                    ) {
                        RadioButton(
                            selected = selectedPayment == "Bank Account",
                            onClick = { selectedPayment = "Bank Account" }
                        )
                        Card(
                            modifier = Modifier.size(30.dp),
                            shape = RoundedCornerShape(7.dp)
                        ) {
                            Image(
                                imageVector = Icons.Default.FoodBank,
                                contentDescription = ""
                            )
                        }
                        Text(text = "Bank Account", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                    }

                    Divider(modifier = Modifier.padding(5.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { selectedPayment = "COD" }
                    ) {
                        RadioButton(
                            selected = selectedPayment == "COD",
                            onClick = { selectedPayment = "COD" }
                        )
                        Text("Cash on Delivery (COD)", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Delivery Method", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(20.dp))

            // Delivery Method Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
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
                        modifier = Modifier.clickable { selectedDelivery = "Door delivery" }
                    ) {
                        RadioButton(
                            selected = selectedDelivery == "Door delivery",
                            onClick = { selectedDelivery = "Door delivery" }
                        )
                        Text("Door delivery", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                    }

                    Divider(modifier = Modifier.padding(5.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { selectedDelivery = "PickUp" }
                    ) {
                        RadioButton(
                            selected = selectedDelivery == "PickUp",
                            onClick = { selectedDelivery = "PickUp" }
                        )
                        Text("PickUp", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Total Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total ", fontSize = 20.sp)
                Text(
                    text = "₹$sum",
                    fontSize = 25.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Place Order Button
            Button(
                onClick = {
                    val profile = profileViewModel.profile.value
                    val dateTime = getCurrentDateTime()
                    cartItems.forEach { item ->
                        val history = HistoryEntity(
                            userName = profile.Name,
                            address = profile.Address,
                            mobileNo = profile.MObileNO,
                            foodId = item.id,
                            foodName = item.name,
                            price = item.price,
                            dateTime = dateTime,
                            photoUrl = item.imageUrl
                        )
                        historyViewmodel.insertHistory(history)
                    }
                    cartViewModel.clearCart()
                    navController.navigate("History")

                    placeOrderCOD(
                        profileViewModel = profileViewModel,
                        cartViewModel = cartViewModel,
                        deliveryMethod = selectedDelivery,
                        context = context
                    ) { success, orderId, error ->
                        if (success) {
                            Toast.makeText(context, "Order Placed Successfully ✅ #$orderId", Toast.LENGTH_SHORT).show()
                            navController.navigate("home")
                        } else {
                            Toast.makeText(context, error ?: "Failed to place order", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA4A0C)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = if (selectedPayment == "COD") "Place Order" else "Process to Payment",
                    fontSize = 20.sp,
                    color = Color.White
                )
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

fun placeOrderCOD(
    profileViewModel: ProfileViewModel,
    cartViewModel: CartViewModel,
    deliveryMethod: String,
    context: Context,
    onComplete: (success: Boolean, orderId: String?, error: String?) -> Unit
) {
    val profile = profileViewModel.profile.value ?: return onComplete(false, null, "Profile not loaded")
    val items = cartViewModel.cartItems

    if (items.isEmpty()) {
        onComplete(false, null, "Cart is empty")
        return
    }

    val total = items.sumOf { it.price }

    val orderData = hashMapOf(
        "userName" to profile.Name,
        "address" to profile.Address,
        "mobile" to profile.MObileNO,
        "items" to items.map { mapOf("id" to it.id, "name" to it.name, "price" to it.price) },
        "totalAmount" to total,
        "deliveryMethod" to deliveryMethod,
        "paymentMode" to "COD",
        "paymentStatus" to "Pending",
        "status" to "Placed",
        "createdAt" to System.currentTimeMillis()
    )

    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("orders").document()

    docRef.set(orderData)
        .addOnSuccessListener {
            cartViewModel.clearCart()
            onComplete(true, docRef.id, null)
        }
        .addOnFailureListener { e ->
            onComplete(false, null, e.localizedMessage)
        }
}
