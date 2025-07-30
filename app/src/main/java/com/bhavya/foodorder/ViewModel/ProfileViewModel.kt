package com.bhavya.foodorder.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bhavya.foodorder.dataclass.Profile

class ProfileViewModel() : ViewModel() {
    var profile = mutableStateOf(Profile("", "", "", ""))
}