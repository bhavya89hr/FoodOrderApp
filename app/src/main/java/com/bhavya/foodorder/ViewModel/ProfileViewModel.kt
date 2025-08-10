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

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        ProfileData::class.java,
        "profile-db"
    ).fallbackToDestructiveMigration()
        .build()

    private val dao = db.ProfileDao()
var name=""
    var profile = mutableStateOf(ProfileEntity(1,"", "", "", ""))
    var allProfiles = mutableStateOf<List<ProfileEntity>>(emptyList())

    init {
       loadProfile()
    }
    fun updateProfile(newProfile: ProfileEntity) {
        viewModelScope.launch {
            dao.insertFavourite(newProfile)
            loadProfile()
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