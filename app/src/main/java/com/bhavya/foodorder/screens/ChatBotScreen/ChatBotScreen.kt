package com.bhavya.foodorder.screens.ChatBotScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.compose.ui.unit.sp
import com.bhavya.foodorder.ViewModel.ChatViewModel
import com.bhavya.foodorder.dataclass.FoodResponse
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.bhavya.foodorder.ViewModel.CartViewModel
import com.bhavya.foodorder.ViewModel.FavouriteViewModel
import com.bhavya.foodorder.ViewModel.SharedSearchViewModel
import com.bhavya.foodorder.screens.HomeScreen.CardDetail


@Composable
fun ChatBotScreen(viewModel: ChatViewModel = viewModel(), navController: NavController,cartViewModel: CartViewModel,searchViewModel: SharedSearchViewModel,favouriteViewModel: FavouriteViewModel) {
    val scrollState = rememberLazyListState()
    var selectedFood by remember { mutableStateOf<FoodItems?>(null) }

    selectedFood?.let { food ->
        CardDetail(
            food = food,
            onClick = { selectedFood = null },
            cartViewModel = cartViewModel,
            searchViewModel = searchViewModel,
            favouriteViewModel = favouriteViewModel,
            navController = navController
        )
        return
    }
    Surface(
        color = Color.White, // Sky blue background
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp, top = 40.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                state = scrollState,
                contentPadding = PaddingValues(8.dp)
            ) {
                items(viewModel.foodSuggestions) { item ->
                    FoodCard(item) { clickedFood ->
                        selectedFood = clickedFood
                    }
                }

                if (viewModel.isLoading) {
                    item {
                        Text("Finding recommendations...", modifier = Modifier.padding(8.dp))
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier.weight(1f),
                    value = viewModel.userMood,
                    onValueChange = { viewModel.userMood = it },
                    placeholder = { Text("Describe what you want...") }
                )
                IconButton(onClick = { viewModel.sendMoodForRecommendation() }) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
            }
            Spacer(Modifier.height(40.dp))

        }
    }
}


@Composable

fun FoodCard(item: FoodResponse, onClick: (FoodItems) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(item.toFoodItems())   // convert & send
            },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.LightGray)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = item.imageUrl.firstOrNull(),
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(item.name, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                Spacer(Modifier.height(5.dp))
                Text(item.category, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp)
                Spacer(Modifier.height(10.dp))
                Text("₹${item.portion.firstOrNull()?.price ?: "--"}", fontSize = 18.sp)
            }
        }
    }
}
fun FoodResponse.toFoodItems(): FoodItems {
    return FoodItems(
        id =  "",
        name = this.name,
        price = 1,
        imageUrl = this.imageUrl.firstOrNull() ?: "",
        description = "",
        category = this.category,
        rating =  0.0,
        isAvailable = true
    )
}
