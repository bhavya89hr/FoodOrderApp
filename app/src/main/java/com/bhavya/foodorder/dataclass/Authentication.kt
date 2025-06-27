package com.bhavya.foodorder.dataclass

data class Authentication(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoginMode: Boolean = true

)
