package com.bhavya.foodorder.ViewModel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.bhavya.foodorder.profileRoom.ProfileDao
import com.bhavya.foodorder.profileRoom.ProfileData
import com.bhavya.foodorder.profileRoom.ProfileEntity
import kotlinx.coroutines.launch

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
var name=""
    private val db = Room.databaseBuilder(
        application,
        ProfileData::class.java,
        "profile-db"
    ).fallbackToDestructiveMigration().build()

    private val dao = db.ProfileDao()
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    var profile = mutableStateOf(ProfileEntity(1,"", "", "", ""))

    init {
        loadProfile()
    }

    fun updateProfile(newProfile: ProfileEntity) {
        viewModelScope.launch {
            dao.insertFavourite(newProfile)
            loadProfile()

            val uid = auth.currentUser?.uid ?: return@launch
            firestore.collection("profiles")
                .document(uid)
                .set(newProfile)
        }
    }

    fun loadProfile() {
        viewModelScope.launch {
            dao.getallItems()?.let {
                profile.value = it
            }
        }
    }
}
