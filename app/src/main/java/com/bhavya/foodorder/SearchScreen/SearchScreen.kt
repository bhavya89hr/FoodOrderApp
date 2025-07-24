package com.bhavya.foodorder.screens.SearchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.bhavya.foodorder.R
@Composable
fun SearchResultScreen(
    navController: NavController,
    query: String,
    results: List<FoodItems>,
    onItemClick: (FoodItems) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Found ${results.size} results",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(results) { food ->
                    SearchResultItem(food = food) {
                        onItemClick(it)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchResultItem(food: FoodItems, onClick: (FoodItems) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { onClick(food) }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.img) // Replace with food.image if needed
                .crossfade(true)
                .build(),
            contentDescription = "Food Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = food.name, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "₦${food.price}", fontSize = 16.sp, color = Color.Red)
        }
    }
}
