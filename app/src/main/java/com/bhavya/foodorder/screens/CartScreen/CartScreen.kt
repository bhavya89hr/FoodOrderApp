package com.bhavya.foodorder.screens.CartScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.bhavya.foodorder.R

@Preview
@Composable
fun CartScreen(){
    Surface(modifier = Modifier.fillMaxSize().background(color = Color(0xFFEFEEEE).copy(alpha = 0.6f))){
        Column(modifier = Modifier.fillMaxSize().padding(20.dp,50.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){
Row(modifier = Modifier.fillMaxWidth().height(70.dp), verticalAlignment = Alignment.CenterVertically){
    Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "", modifier = Modifier.size(35.dp))
    Text(text = "Cart", fontWeight = FontWeight.SemiBold, fontSize = 30.sp,modifier = Modifier.padding(120.dp,0.dp))
}
            Spacer(modifier = Modifier.height(50.dp))

            Text(text="Swipe on item to delete",fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(30.dp))
            Card(
                modifier = Modifier
                    .height(200.dp).fillMaxWidth().padding(0.dp, 10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                Row(modifier = Modifier.padding(10.dp)) {
                    Box(modifier = Modifier.height(125.dp).width(125.dp).align(Alignment.CenterVertically) ) {
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = "Default Profile Picture",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Column(modifier = Modifier.fillMaxHeight().width(205.dp).padding(0.dp, 20.dp).verticalScroll(rememberScrollState())) {
Text(text="veggies tomato mix", fontWeight = FontWeight.SemiBold, fontSize = 25.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text=" #1990",fontWeight = FontWeight.SemiBold, fontSize = 25.sp,color=Color.Red)
                        Box(modifier = Modifier.height(90.dp).width(100.dp).background(color = Color.Red))
                        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {

                        }
                    }
                }
            }
        }

    }
}