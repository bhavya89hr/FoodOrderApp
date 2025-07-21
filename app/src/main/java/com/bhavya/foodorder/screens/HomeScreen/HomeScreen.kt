package com.bhavya.foodorder.screens.HomeScreen

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.bhavya.foodorder.R
import com.bhavya.foodorder.dataclass.FoodItem
import com.bhavya.foodorder.screens.DrawerScreeen.DrawerFun
import com.bhavya.foodorder.screens.DrawerScreeen.DrawerItems
import com.bhavya.foodorder.screens.profileScreen.Navigation.ProfileNav
import kotlinx.coroutines.launch

val LightGrayCustom = Color(0xFFEFEEEE).copy(alpha = 0.6f)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: FoodViewModel = viewModel(),navController: NavController ) {
    var selectedCategory by remember { mutableStateOf("Food") }
    val categories = listOf("Food", "Snacks", "Drinks", "Desserts")
    val foodItems = viewModel.foodItems
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    var searchQuery by remember { mutableStateOf("") }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
        Scaffold(
            bottomBar = {
                Column(modifier = Modifier.padding(0.dp,50.dp)) {

                  Bottombar(navController)
                }
            }
           , topBar = {
                TopAppBar(
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
                        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {


                            Spacer(modifier = Modifier.height(12.dp))

                            if (isLoading) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                            } else if (!errorMessage.isNullOrEmpty()) {
                                Text(
                                    text = errorMessage,
                                    color = Color.Red,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            } else {
                                LazyRow {
                                    items(foodItems) { item ->
                                        FoodItemCard(foodItem = item)
                                        {navController.navigate("detail/${item.name}/${item.price}")}
                                        }
                                    }
                                }
                            }
                        }


                    }

            }
        )
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
fun FoodItemCard(foodItem: FoodItem,onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp).clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = "https://images.unsplash.com/photo-1600891964599-f61ba0e24092",
                contentDescription = foodItem.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = foodItem.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "₦${foodItem.price.toInt()}",
                color = Color(0xFFFF5722),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}





