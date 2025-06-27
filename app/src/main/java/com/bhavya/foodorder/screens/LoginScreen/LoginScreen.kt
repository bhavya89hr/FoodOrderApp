package com.bhavya.foodorder.screens.LoginScreen

import android.R.attr.maxHeight
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bhavya.foodorder.R

import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bhavya.foodorder.ViewModel.AuthViewModel
@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    val isLoginMode = state.isLoginMode

    Box(modifier = Modifier.fillMaxSize()) {
        // Top surface with logo and tab
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp),
            shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp),
            color = Color.White,
            shadowElevation = 12.dp
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.groupp),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(180.dp)
                )

                // Tab Selector
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 32.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        // Login Tab
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Login",
                                color = if (isLoginMode) Color.Black else Color.Black.copy(alpha = 0.5f),
                                fontWeight = FontWeight.Bold,
                                fontSize = 19.sp,
                                modifier = Modifier
                                    .padding(9.dp)
                                    .clickable {
                                        if (!isLoginMode) viewModel.toggleMode()
                                    }
                            )
                            if (isLoginMode) {
                                Box(
                                    modifier = Modifier
                                        .height(3.dp)
                                        .width(120.dp)
                                        .background(Color(0xFFFF3D00), shape = RoundedCornerShape(1.5.dp))
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(120.dp))

                        // Sign-up Tab
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Sign-up",
                                color = if (!isLoginMode) Color.Black else Color.Black.copy(alpha = 0.5f),
                                fontWeight = FontWeight.Bold,
                                fontSize = 19.sp,
                                modifier = Modifier
                                    .padding(9.dp)
                                    .clickable {
                                        if (isLoginMode) viewModel.toggleMode()
                                    }
                            )
                            if (!isLoginMode) {
                                Box(
                                    modifier = Modifier
                                        .height(3.dp)
                                        .width(120.dp)
                                        .background(Color(0xFFFF3D00), shape = RoundedCornerShape(1.5.dp))
                                )
                            }
                        }
                    }
                }
            }
        }

        // Form Section
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 480.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = if (isLoginMode) "Login" else "Sign Up",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            )

            Text("Email address", color = Color.Gray, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp))
            TextField(
                value = state.email,
                onValueChange = viewModel::onEmailChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Password", color = Color.Gray, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp))
            TextField(
                value = state.password,
                onValueChange = viewModel::onPasswordChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black
                )
            )

            if (state.error != null) {
                Text(state.error!!, color = Color.Red, modifier = Modifier.padding(12.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Forgot passcode?", color = Color(0xFFFF3D00), fontWeight = FontWeight.Medium, modifier = Modifier.align(Alignment.End))

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    viewModel.submit {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp)
                    .height(63.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF3D00))
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White, strokeWidth = 2.dp)
                } else {
                    Text(if (isLoginMode) "Login" else "Sign Up", color = Color.White, fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
