package com.bhavya.foodorder.screens.HomeScreen

import android.view.Surface
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.bhavya.foodorder.ViewModel.FoodViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.request.ImageRequest
import com.bhavya.foodorder.DataManager
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.bhavya.foodorder.R
import com.bhavya.foodorder.ViewModel.CartViewModel
import com.bhavya.foodorder.screens.DrawerScreeen.DrawerFun
import com.bhavya.foodorder.screens.DrawerScreeen.DrawerItems
import kotlinx.coroutines.launch

val LightGrayCustom = Color(0xFFEFEEEE).copy(alpha = 0.6f)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: FoodViewModel = viewModel(),navController: NavController,cartViewModel: CartViewModel) {
    var selectedCategory by remember { mutableStateOf("Food") }
    val categories = listOf("Food", "Snacks", "Drinks", "Desserts")
    val foodItems = viewModel.foodItems
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    var searchQuery by remember { mutableStateOf("") }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedFood by remember { mutableStateOf<FoodItems?>(null) }

    val FoodItems = DataManager.data.toList()


    selectedFood?.let { food ->
        CardDetail(
            food = food,
            onClick = { selectedFood = null },
            navController = navController,
            cartViewModel = cartViewModel
        )
        return
    }
    ModalNavigationDrawer(
        drawerContent = {
            DrawerFun { item ->
                scope.launch { drawerState.close() }
                when (item) {
                    DrawerItems.profile -> { /* Navigate or Handle */
                    }

                    DrawerItems.Orders -> { /* Navigate */
                    }

                    DrawerItems.SignOut -> { /* Handle Sign-out */
                    }
                    // Add navigation or logic as needed
                    else -> {}
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(containerColor = LightGrayCustom,
            bottomBar = {
                Column(modifier = Modifier.padding(0.dp,50.dp)) {

                    Bottombar(navController)
                }
            }
            , topBar = {
                TopAppBar(colors = TopAppBarDefaults.topAppBarColors(LightGrayCustom) ,
                    title = { /* No title as per your screenshot */ },
                    navigationIcon = {
                        IconButton(
                            onClick = {scope.launch { drawerState.open() }},
                            modifier = Modifier.padding(start = 12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.vector), // Replace with your icon
                                contentDescription = "Menu", modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { navController.navigate("Cart") },
                            modifier = Modifier.padding(end = 12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.vector1), // Replace with your icon
                                contentDescription = "Cart",
                                tint = Color.LightGray,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    },

                    )
            },

            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        // Define what happens when it's clicked
                        navController.navigate("ChatBot")
                    },
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.size(80.dp).clickable { navController.navigate("ChatBot") }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.options), // 🔁 Replace with your FAB image
                        contentDescription = "FAB Icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
            },
            content = { paddingValues ->
                // Your screen content goes here
                Box(modifier = Modifier.padding(paddingValues)) {
                    // Main content
                    Column(modifier = Modifier.padding(30.dp,20.dp)) {
                        Text(
                            text = "Delicious \n\nFood For You", fontSize = 40.sp,
                            fontWeight = FontWeight.W900, color = Color.Black
                        )

                        Spacer(Modifier.height(8.dp))
                        SearchBar(
                            query = searchQuery,
                            onQueryChange = { searchQuery = it }
                        )
                        Spacer(Modifier.height(38.dp))
                        CategoryList(
                            categories = categories,
                            selectedCategory = selectedCategory,
                            onCategorySelected = { selectedCategory = it }
                        )
                        val filteredItems = FoodItems.filter {
                            it.category.equals(selectedCategory, ignoreCase = true) &&
                                    it.name.contains(searchQuery, ignoreCase = true)
                        }
                        if (filteredItems.isEmpty()) {
                            Text(
                                text = "No items match your search.",
                                color = Color.Gray,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        } else {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(filteredItems) { food ->
                                    FoodItemCard(food = food) {
                                        selectedFood = it
                                    }
                                }
                            }
                        }

                            }

                        }

                    })
                }
            }


@Composable
fun SearchBar(

    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            Text("Search", color = Color.Gray, fontSize = 18.sp)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            focusedContainerColor = LightGrayCustom,
            unfocusedContainerColor =LightGrayCustom,
            disabledContainerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(33.dp),
        singleLine = true
    )
}
@Composable
fun CategoryList(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(categories) { category ->
            val isSelected = category == selectedCategory

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { onCategorySelected(category) }
            ) {
                Text(
                    text = category,
                    color = if (isSelected) Color.Red else Color.Black,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .width(80.dp)
                        .background(if (isSelected) Color.Red else Color.Transparent)
                )
            }
        }
    }
}

sealed class bottomItems(val icon1: ImageVector,val icon2: ImageVector,val id: String){
    object Home: bottomItems(Icons.Outlined.Home, Icons.Filled.Home,"home")
    object fav: bottomItems(Icons.Outlined.FavoriteBorder,Icons.Filled.Favorite,"fav")
    object Profile: bottomItems(Icons.Outlined.Person,Icons.Filled.Person,"profile")
    object History: bottomItems(Icons.Outlined.Refresh,Icons.Filled.Refresh,"history")
}


@Composable
fun Bottombar(navController: NavController,
){

    val items=listOf(
        bottomItems.Home,
        bottomItems.fav,
        bottomItems.Profile,
        bottomItems.History
    )
    var SelectedItems by remember { mutableStateOf("home") }
    Row(modifier = Modifier.fillMaxWidth().height(30.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        items.forEach {item->
            val isSelected=item.id==SelectedItems
            Image(imageVector = if (isSelected)item.icon2 else item.icon1, contentDescription = "",modifier = Modifier.size(31.dp).clickable{SelectedItems=item.id
                when (item.id){
                    "home"->navController.navigate("home")
                    "profile"->navController.navigate("profile")
                }
            }.then(if (isSelected) Modifier.shadow(30.dp,RoundedCornerShape(50)) else Modifier), colorFilter = ColorFilter.tint(if (isSelected) Color.Red else Color.Black))
        }

    }
}

@Composable
fun FoodItemCard(food: FoodItems,onClick: (FoodItems) -> Unit){
//    Log.d("FoodImage", "Image URL: ${food.image}")
    Box(
        modifier = Modifier
            .height(400.dp)
            .width(320.dp)
            .background(color = LightGrayCustom)
            .clickable{onClick(food)},
        contentAlignment = Alignment.TopCenter
    ) {

        // Main white card box
        Box(
            modifier = Modifier
                .padding(top = 60.dp) // Push it down to allow space for the image
                .height(360.dp)
                .width(270.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.White)
            , contentAlignment = Alignment.Center
        ){

            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(100.dp))
                Text(text=food.name, fontSize = 33.sp, fontWeight = FontWeight.Medium, modifier = Modifier)
                Spacer(modifier = Modifier.height(35.dp))
                Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                    Text(text = "₹",fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.Red)
//                   Text(text = ",",fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.Red)
                    Text(text =food.price.toString(),fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.Red)
                }

            }

        }
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.LightGray), // Helps reveal if image fails
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.img)
                    .crossfade(true)
                    .build(),
                contentDescription = "Food Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }



    }
}

@Composable
fun CardDetail(onClick: (FoodItems) -> Unit,food: FoodItems,navController: NavController,cartViewModel: CartViewModel){
    androidx.compose.material3.Surface(modifier = Modifier.fillMaxSize().background(Color(0xFFEFEEEE).copy(alpha = 0.6f))) {
        Column(modifier = Modifier.fillMaxSize().padding(10.dp,40.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp,0.dp),
                horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Image(imageVector = Icons.Default.PlayArrow, contentDescription = "", modifier = Modifier.clickable {
                    navController.navigate("home")      }
                    .rotate(180f),
                    colorFilter = ColorFilter.tint(Color.Black))
                Image(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "")
//               Text(text="Back", color = Color.Black, fontWeight = FontWeight.Bold, modifier =Modifier, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray), // Helps reveal if image fails
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(id=R.drawable.img),contentDescription = "Food Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize())
            }
            Text(text= food.name, fontSize = 32.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(25.dp))

            Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Text(text = "₹",fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color(0xFFFA4A0C))
//                   Text(text = ",",fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color.Red)
                Text(text =food.price.toString(),fontSize = 24.sp, fontWeight = FontWeight.Medium, color = Color(0xFFFA4A0C))
            }
            Column(modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(0.dp,50.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top) {
                Text(text="Delivery Info" , fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(15.dp))
                Text(text="Delivered between monday aug and thursday 20 from 8pm to 91:32 pm" , fontSize = 22.sp, fontWeight = FontWeight.Light)
                Spacer(modifier = Modifier.height(35.dp))
                Text(text="Return Policy" , fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(15.dp))

                Text(text="All our foods are double checked before leaving our stores so by any case you found a broken food please contact our hotline immediately." , fontSize = 22.sp, fontWeight = FontWeight.Light)
                Spacer(modifier = Modifier.height(45.dp))
                Button(onClick = { cartViewModel.addToCart(food)
                    navController.navigate("Cart")}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA4A0C)), modifier = Modifier.height(70.dp).fillMaxWidth()) {

                    Text(text="Add to Cart" , fontSize = 22.sp, fontWeight = FontWeight.SemiBold)

                }

            }

        }

    }

}