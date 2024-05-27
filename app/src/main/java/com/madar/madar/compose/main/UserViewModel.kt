package com.madar.madar.compose.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.madar.madar.data.User
import com.madar.madar.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val allUsers: LiveData<List<User>> = repository.allUsers.asLiveData()


    fun saveUser(name: String, age: Int, jobTitle: String, gender: String) {
        viewModelScope.launch {
            val user = User(name = name, age = age, jobTitle = jobTitle, gender = gender)
            repository.insertTask(user)
        }
    }


}
