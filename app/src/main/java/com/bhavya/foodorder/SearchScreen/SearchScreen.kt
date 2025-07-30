package com.bhavya.foodorder.screens.SearchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bhavya.foodorder.DataManager
import com.bhavya.foodorder.FoodItemsDataClass.FoodItems
import com.bhavya.foodorder.R
import com.bhavya.foodorder.ViewModel.CartViewModel
import com.bhavya.foodorder.ViewModel.FavouriteViewModel
import com.bhavya.foodorder.ViewModel.SharedSearchViewModel
import com.bhavya.foodorder.screens.HomeScreen.CardDetail
import com.bhavya.foodorder.screens.HomeScreen.FoodItemCard
import com.bhavya.foodorder.screens.HomeScreen.LightGrayCustom
import com.bhavya.foodorder.screens.HomeScreen.SearchBar

@Composable
fun SearchResultScreen(
    navController: NavController,
    query: String,
    searchViewModel: SharedSearchViewModel,
cartViewModel: CartViewModel,
    favouriteViewModel: FavouriteViewModel
) {

    var selectedFood by remember { mutableStateOf<FoodItems?>(null) }
//    var selecteditem by remember { mutableStateOf<FoodItems/>(null) }
    val FoodItems = DataManager.data.toList()
    val Items=searchViewModel.searchResults
    val searchQuery=searchViewModel.searchQuery
var newitems by remember { mutableStateOf(Items) }
    var searchQueryTwo by remember { mutableStateOf(searchQuery) }
    selectedFood?.let { food ->
        CardDetail(
            food = food,
            onClick = { selectedFood = null },
            navController = navController,
            cartViewModel =cartViewModel,
            searchViewModel = searchViewModel,
            favouriteViewModel = favouriteViewModel
        )
        return
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGrayCustom)
            .padding(0.dp,20.dp)

    ) {

        Column(modifier = Modifier.padding(10.dp,30.dp)) {
         Box(modifier = Modifier.fillMaxWidth()){
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

             }
         }
            Spacer(modifier = Modifier.height(10.dp))
           SearchBar(query =searchQueryTwo,
               onQueryChange = {searchQueryTwo=it})

            Spacer(modifier = Modifier.height(20.dp))
            val fooditem=FoodItems.filter {

                it.name.contains(searchQueryTwo, ignoreCase = true)
            }

            Text(
                text = "Found ${fooditem.size} results",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
if (fooditem.isEmpty()){
    Column(
        modifier = Modifier.fillMaxWidth()
            .height(600.dp).padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            imageVector = Icons.Outlined.Search,
            contentDescription = "",
            modifier = Modifier.size(300.dp),
            colorFilter = ColorFilter.tint(Color.LightGray)
        )
        Text(
            text = "No oreders Yet",
            fontSize = 42.sp,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Try searching the item with a different keyword.",
            fontSize = 15.sp
        )


    }
}else LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().background(color = LightGrayCustom)
            ) {
                items(fooditem) { food ->
                    FoodItemCard (food = food){
                        selectedFood=it
                    }


                }
            }
        }
    }
}

@Composable
fun SearchResultItem(food: FoodItems, onClick: (FoodItems) -> Unit) {
    Box(
        modifier = Modifier
            .height(400.dp)
            .width(320.dp)
            .background(color = LightGrayCustom)
            .clickable{onClick(food)},
        contentAlignment = Alignment.TopCenter
    ) {


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
