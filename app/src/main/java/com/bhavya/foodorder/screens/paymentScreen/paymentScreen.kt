package com.bhavya.foodorder.screens.paymentScreen

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bhavya.foodorder.ViewModel.CartViewModel
import com.bhavya.foodorder.ViewModel.ProfileViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun PaymentScreen(
    profileViewModel: ProfileViewModel,
    navController: NavController,
    cartViewModel: CartViewModel,
    value: String
) {
    val cartItem = cartViewModel.cartItems
    val sum = cartItem.sumOf { it.price }
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        Column(modifier = Modifier.fillMaxSize().padding(30.dp), horizontalAlignment = Alignment.Start) {
            Row(
                modifier = Modifier.fillMaxWidth().height(70.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp).clickable { navController.navigate("Delivery") }
                )
                Text("Checkout", fontWeight = FontWeight.Normal, fontSize = 25.sp, modifier = Modifier.padding(start = 110.dp))
            }

            Spacer(Modifier.height(20.dp))
            Text("Payment", fontWeight = FontWeight.SemiBold, fontSize = 35.sp)
            Spacer(Modifier.height(30.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text("address method", fontSize = 20.sp, fontWeight = FontWeight.Normal)
            }

            Spacer(Modifier.height(40.dp))
            // keep UI as-is
            var selectedMetho by remember { mutableStateOf("Card") }

            Card(modifier = Modifier.fillMaxWidth().height(160.dp), elevation = 6.dp, shape = RoundedCornerShape(10.dp)) {
                Column(modifier = Modifier.fillMaxSize().padding(20.dp, 10.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { selectedMetho = "Card" }) {
                        RadioButton(selected = selectedMetho == "Card", onClick = { selectedMetho = "Card" })
                        Card(modifier = Modifier.size(30.dp), shape = RoundedCornerShape(7.dp)) {
                            Icon(Icons.Default.CreditCard, contentDescription = null)
                        }
                        Text("Card", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                    }
                    Divider(modifier = Modifier.padding(5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { selectedMetho = "Bank Account" }) {
                        RadioButton(selected = selectedMetho == "Bank Account", onClick = { selectedMetho = "Bank Account" })
                        Card(modifier = Modifier.size(30.dp), shape = RoundedCornerShape(7.dp)) {
                            Icon(Icons.Default.FoodBank, contentDescription = null)
                        }
                        Text("Bank Account", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            Spacer(Modifier.height(30.dp))
            Text("Delivery Method", fontWeight = FontWeight.Normal, fontSize = 20.sp)
            Spacer(Modifier.height(30.dp))
            var selectedMethod by remember { mutableStateOf(value) }

            Card(modifier = Modifier.fillMaxWidth().height(160.dp), elevation = 6.dp, shape = RoundedCornerShape(10.dp)) {
                Column(modifier = Modifier.fillMaxSize().padding(20.dp, 10.dp), verticalArrangement = Arrangement.SpaceEvenly) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { selectedMethod = "Door delivery" }) {
                        RadioButton(selected = selectedMethod == "Door delivery", onClick = { selectedMethod = "Door delivery" })
                        Text("Door delivery", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                    }
                    Divider(modifier = Modifier.padding(5.dp))
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { selectedMethod = "PickUp" }) {
                        RadioButton(selected = selectedMethod == "PickUp", onClick = { selectedMethod = "PickUp" })
                        Text("PickUp", fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            Spacer(Modifier.height(30.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total ", fontSize = 20.sp, fontWeight = FontWeight.Normal)
                Text(sum.toString(), fontSize = 25.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            }

            Spacer(Modifier.height(30.dp))

            // 👉 Always place COD (no UI change). You can switch this to selectedMetho later.
            Button(
                onClick = {
                    placeOrderCOD(
                        profileViewModel = profileViewModel,
                        cartViewModel = cartViewModel,
                        deliveryMethod = selectedMethod,   // "Door delivery" / "PickUp"
                        context = context
                    ) { success, orderId, error ->
                        if (success) {
                            Toast.makeText(context, "Order Placed! #$orderId", Toast.LENGTH_SHORT).show()
                            navController.navigate("home")
                        } else {
                            Toast.makeText(context, error ?: "Failed to place order", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA4A0C)),
                modifier = Modifier.fillMaxWidth().height(55.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Process to Payment", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}


fun placeOrderCOD(
    profileViewModel: ProfileViewModel,
    cartViewModel: CartViewModel,
    deliveryMethod: String,
    context: Context,
    onComplete: (success: Boolean, orderId: String?, error: String?) -> Unit
) {
    val profile = profileViewModel.profile.value
    val items = cartViewModel.cartItems

    if (items.isEmpty()) {
        onComplete(false, null, "Cart is empty")
        return
    }

    val total = items.sumOf { it.price }

    // Build a Map so it works even if your data class changes
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
    val docRef = db.collection("orders").document() // create id first to return it
    docRef.set(orderData)
        .addOnSuccessListener {
            // clear local cart
            cartViewModel.clearCart()
            onComplete(true, docRef.id, null)
        }
        .addOnFailureListener { e ->
            onComplete(false, null, e.message)
        }
}
