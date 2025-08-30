package com.bhavya.foodorder.screens.CartScreen

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.bhavya.foodorder.R
import com.bhavya.foodorder.ViewModel.CartViewModel
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import com.bhavya.foodorder.ViewModel.FavouriteViewModel
import com.bhavya.foodorder.ViewModel.SharedSearchViewModel
import com.bhavya.foodorder.screens.HomeScreen.CardDetail
import com.bhavya.foodorder.screens.HomeScreen.LightGrayCustom


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavouriteScreen(favouriteViewModel: FavouriteViewModel,navController: NavController,cartViewModel: CartViewModel,searchViewModel: SharedSearchViewModel) {

    val cartItems = favouriteViewModel.favouriteItems
    var selectedItems by remember { mutableStateOf<FoodItems?>(null) }
    selectedItems?.let { food ->
        CardDetail(
            onClick = { selectedItems = null },
            food = food,
            navController = navController,
            cartViewModel = cartViewModel,
            searchViewModel = searchViewModel,
            favouriteViewModel,
        )
        return
    }
    androidx.compose.material.Surface(
        modifier = Modifier.fillMaxSize().background(color = LightGrayCustom)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Content Column
            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp, 50.dp, 20.dp)
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
                        text = "Favourite",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(start = 90.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                if (cartItems.isEmpty()) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(50.dp))

                        Image(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "",
                            modifier = Modifier.size(250.dp),
                            colorFilter = ColorFilter.tint(Color.LightGray)
                        )
                        Text(
                            text = "No Favourite Yet",
                            fontSize = 42.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            text = "Hit the orange button down below to Create an order",
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.height(200.dp))

                        Button(
                            onClick = { navController.navigate("Home") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFA4A0C)
                            ),
                            modifier = Modifier.fillMaxWidth().height(55.dp),
                            shape = RoundedCornerShape(20.dp)
                        ) {

                            Text(text = "Place An Order", fontSize = 20.sp)
                        }
                    }

                } else {
                    val uniqueItem = cartItems.distinctBy { it.id }

                    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                        uniqueItem.forEach { food ->

                            val dismissState = rememberDismissState(
                                confirmStateChange = {
                                    if (it == DismissValue.DismissedToStart) {
                                        favouriteViewModel.removeFromFavourites(food) // Remove from cart
                                        true
                                    } else false
                                }
                            )

                            SwipeToDismiss(
                                state = dismissState,
                                directions = setOf(
                                    DismissDirection.StartToEnd,
                                    DismissDirection.EndToStart
                                ),
                                background = {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.White)
                                            .padding(20.dp),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Delete,
                                            contentDescription = "Delete",
                                            tint = Color.Red,
                                            modifier = Modifier.size(30.dp)
                                        )
                                    }
                                },
                                dismissContent = {

                                    Card(
                                        modifier = Modifier
                                            .height(150.dp)
                                            .fillMaxWidth()
                                            .padding(vertical = 10.dp)
                                            .clickable {
                                                selectedItems = food
                                            },
                                        colors = CardDefaults.cardColors(containerColor = Color.White),
                                        shape = RoundedCornerShape(25.dp),
                                        elevation = CardDefaults.elevatedCardElevation(5.dp)
                                    ) {
                                        Row(modifier = Modifier.padding(10.dp)) {
                                            val context = LocalContext.current
                                            val imageResId = remember(food.imageUrl) {
                                                context.resources.getIdentifier(
                                                    food.imageUrl,
                                                    "drawable",
                                                    context.packageName
                                                )
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxHeight()
                                                    .width(100.dp)
                                                    .clip(CircleShape)
                                                    .background(Color.LightGray), // Helps reveal if image fails
                                                contentAlignment = Alignment.Center
                                            ) {
                                                if (imageResId != 0) {
                                                    Image(
                                                        painter = painterResource(id = imageResId),
                                                        contentDescription = "Food Image",
                                                        contentScale = ContentScale.Crop,
                                                        modifier = Modifier.fillMaxSize()
                                                    )
                                                } else {

                                                    Box(
                                                        modifier = Modifier.fillMaxSize()
                                                            .background(Color.Gray),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Text("No Image", color = Color.White)
                                                    }
                                                }
                                            }
                                            Spacer(modifier = Modifier.width(20.dp))
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxHeight()
                                                    .width(205.dp)
                                                    .padding(vertical = 5.dp)
                                                    .verticalScroll(rememberScrollState())
                                            ) {
                                                Text(
                                                    text = food.name,
                                                    fontWeight = FontWeight.SemiBold,
                                                    fontSize = 22.sp
                                                )
                                                Spacer(modifier = Modifier.height(5.dp))
                                                Row(
                                                    modifier = Modifier.fillMaxWidth()
                                                        .padding(10.dp),
                                                    horizontalArrangement = Arrangement.Start
                                                ) {
                                                    Text(
                                                        text = "₹",
                                                        fontSize = 18.sp,
                                                        fontWeight = FontWeight.Medium,
                                                        color = Color(0xFFFA4A0C)
                                                    )
//                   Text(text = ",",fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.Red)
                                                    Text(
                                                        text = food.price.toString(),
                                                        fontSize = 18.sp,
                                                        fontWeight = FontWeight.Medium,
                                                        color = Color(0xFFFA4A0C)
                                                    )
                                                }
//                                            Box(
//                                                modifier = Modifier
//                                                    .height(25.dp)
//                                                    .width(60.dp)
//                                                    .clip(RoundedCornerShape(20.dp))
//                                                    .background(color = Color.Red)
//                                                    .align(Alignment.End)
//                                            ) {
////                                                Row(
////                                                    modifier = Modifier.fillMaxSize(),
////                                                    verticalAlignment = Alignment.CenterVertically,
////                                                    horizontalArrangement = Arrangement.SpaceAround
////                                                ) {
////                                                    Text(
////                                                        text = "+",
////                                                        fontSize = 20.sp,
////                                                        color = Color.White,
////                                                        modifier = Modifier.clickable {
////                                                            count.value = count.value + 1
////                                                        })
////                                                    Text(
////                                                        text = count.value.toString(),
////                                                        fontSize = 19.sp,
////                                                        color = Color.White
////                                                    )
////                                                    Text(
////                                                        text = "-",
////                                                        fontSize = 20.sp,
////                                                        color = Color.White,
////                                                        modifier = Modifier.clickable {
////                                                            count.value = count.value - 1
////                                                        })
////                                                }
//                                            }
                                            }
                                        }
                                    }
                                }
                            )

                        }
                    }
                }
            }
        }
    }
}