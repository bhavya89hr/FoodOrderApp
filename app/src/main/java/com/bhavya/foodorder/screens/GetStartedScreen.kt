package com.bhavya.foodorder.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.pm.ShortcutInfoCompat.Surface
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bhavya.foodorder.R
import com.bhavya.foodorder.navigation.AppScreens
import kotlinx.coroutines.delay
@Composable
fun GetStarted(navController: NavController) {
    val DarkRed = Color(0xFF8B0000)
    val LightRed = Color(0xFFFF6F61)

    var animateUp by remember { mutableStateOf(false) }

    val offsetY by animateDpAsState(
        targetValue = if (animateUp) (-1000).dp else 0.dp, // adjust value if needed
        animationSpec = tween(durationMillis = 600),
        label = "slide-up"
    )

    if (animateUp) {
        LaunchedEffect(Unit) {
            delay(400)
            navController.navigate(AppScreens.LoginScreen.route)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Red
    ) {
        Box(modifier = Modifier.offset(y = offsetY)) {
            Column {
                Surface(
                    modifier = Modifier
                        .offset(x = 49.dp, y = 56.dp)
                        .size(width = 73.dp, height = 73.dp),
                    shape = RoundedCornerShape(percent = 50),
                    color = Color.White
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.group3),
                            contentDescription = "",
                            modifier = Modifier
                                .height(49.6.dp)
                                .width(45.86.dp)
                        )
                    }
                }

                Spacer(Modifier.height(100.dp))

                // 🔴 Headline Text
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Food for\n\n Everyone",
                        fontSize = 58.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 60.dp)
                    )
                }

                Spacer(Modifier.height(85.dp))

                // 🔴 Row of Images
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.toyfaces),
                            contentDescription = "Image 1",
                            modifier = Modifier.size(300.dp),
                            alignment = Alignment.TopStart
                        )

                        Image(
                            painter = painterResource(id = R.drawable.toyfaces2),
                            contentDescription = "Image 2",
                            modifier = Modifier.size(300.dp),
                            alignment = Alignment.TopEnd
                        )
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))

                // 🔴 Button
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CustomWhiteButton(text = "Get Started") {
                        animateUp = true
                    }
                }
            }
        }
    }
}

@Composable
fun CustomWhiteButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Red
        ),
        shape = RoundedCornerShape(16.dp), // adjust roundness here
        modifier = Modifier
            .height(70.dp)
            .width(320.dp) // optional size
    ) {
        Text(
            fontSize = 22.sp,
            text = text,
            color = Color.Red,
            fontWeight = FontWeight.Bold
        )
    }
}

