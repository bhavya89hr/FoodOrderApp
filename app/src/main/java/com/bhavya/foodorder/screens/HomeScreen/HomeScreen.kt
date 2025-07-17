package com.bhavya.foodorder.screens.HomeScreen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.bhavya.foodorder.R
import com.bhavya.foodorder.dataclass.FoodItem
import com.bhavya.foodorder.screens.DrawerScreeen.DrawerFun
import com.bhavya.foodorder.screens.DrawerScreeen.DrawerItems
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
            DrawerFun(){ items ->
                 scope.launch { drawerState.close() }

             }
        },
        drawerState = drawerState
    ) {
        Surface(tonalElevation = 6.dp,
            shadowElevation = 6.dp,
            shape = RoundedCornerShape(0.dp)) {  }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { /* No title as per your screenshot */ },
                    navigationIcon = {
                        IconButton(
                            onClick = { scope.launch { drawerState.open() }},
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
            content = { paddingValues ->
                // Your screen content goes here
                Box(modifier = Modifier.padding(paddingValues)) {
                    // Main content
                    Column(modifier = Modifier.padding(30.dp)) {
                        Text(
                            text = "Delicious \n\nFood For You", fontSize = 40.sp,
                            fontWeight = FontWeight.W900, color = Color.Black
                        )
                        Spacer(Modifier.height(8.dp))
//                        SearchBar(
//                            query = searchQuery,
//                            onQueryChange = { searchQuery = it }
//                        )
//                        Spacer(Modifier.height(38.dp))
//                        CategoryList(
//                            categories = categories,
//                            selectedCategory = selectedCategory,
//                            onCategorySelected = { selectedCategory = it }
//                        )


                    }
                }
            }
        )
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
                unfocusedContainerColor = LightGrayCustom,
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
}



