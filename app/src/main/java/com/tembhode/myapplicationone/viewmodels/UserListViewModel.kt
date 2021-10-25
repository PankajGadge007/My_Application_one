package com.tembhode.myapplicationone.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tembhode.myapplicationone.data.UserDataRepository
import com.tembhode.myapplicationone.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListViewModel(var userDataRepository: UserDataRepository) : ViewModel() {

    var usersList = MutableLiveData<List<User>>()

    fun getUsersList() {
        CoroutineScope(Dispatchers.Main).launch {
            val mUsersList = getUsers()
            usersList.value = mUsersList
        }
    }

    private suspend fun getUsers(): List<User> = withContext(Dispatchers.IO) {
        return@withContext userDataRepository.getAllUser()
    }

    fun updateData(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDataRepository.updateUser(user)
        }
    }

    fun deleteData(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userDataRepository.deleteUser(user)
        }
    }
}