package com.bhavya.foodorder.ViewModel

import android.app.Application
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavya.foodorder.API.RetrofitInstance
import com.bhavya.foodorder.dataclass.FoodItem
import com.bhavya.foodorder.screens.getRealPathFromUri
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject


 // Remove this if not using Hilt
 class FoodViewModel @Inject constructor(
     private val context: Application
 ) : AndroidViewModel(context) {

     private val _foodItems = mutableStateOf<List<FoodItem>>(emptyList())
     val foodItems: List<FoodItem> get() = _foodItems.value

     var isLoading by mutableStateOf(false)
         private set

     var errorMessage by mutableStateOf<String?>(null)
         private set

     init {
         fetchFoodItems()
     }

     fun fetchFoodItems() {
         viewModelScope.launch {
             isLoading = true
             try {
                 val response = RetrofitInstance.api.getFoodItems()
                 _foodItems.value = response
                 errorMessage = null
             } catch (e: Exception) {
                 e.printStackTrace()
                 Log.e("FetchError", "Error: ${e.localizedMessage}")
                 errorMessage = "Failed to load food items"
             } finally {
                 isLoading = false
             }
         }
     }

     fun addFoodItem(
         name: String,
         price: String,
         imageUri: Uri,
         onSuccess: () -> Unit
     ) {
         viewModelScope.launch {
             try {
                 val namePart = name.toRequestBody("text/plain".toMediaTypeOrNull())
                 val pricePart = price.toRequestBody("text/plain".toMediaTypeOrNull())

                 val file = File(getRealPathFromUri(imageUri, context))
                 val imagePart = MultipartBody.Part.createFormData(
                     "photo", file.name, file.asRequestBody("image/*".toMediaTypeOrNull())
                 )

                 RetrofitInstance.api.addFoodItem(namePart, pricePart, imagePart)

                 fetchFoodItems()
                 onSuccess()
             } catch (e: Exception) {
                 e.printStackTrace()
                 errorMessage = "Failed to add food item"
             }
         }
     }

     private fun getRealPathFromUri(uri: Uri, context: Context): String {
         val contentResolver = context.contentResolver
         val cursor = contentResolver.query(uri, null, null, null, null)
         cursor?.moveToFirst()
         val index = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA) ?: -1
         val path = if (index != -1) cursor?.getString(index) else null
         cursor?.close()
         return path ?: throw IllegalArgumentException("Unable to get real path from URI")
     }
 }
