package com.tembhode.myapplicationone.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tembhode.myapplicationone.data.UserDataRepository
import com.tembhode.myapplicationone.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(var userDataRepository: UserDataRepository) : ViewModel() {

    var allUserList = MutableLiveData<List<User>>()
    var isInserted = MutableLiveData<Long>()

    fun insertData(user: User) {
        CoroutineScope(Dispatchers.Main).launch {
            val idLong = insertUser(user)
            isInserted.value = idLong
        }
    }

    private suspend fun insertUser(user: User): Long =
        withContext(Dispatchers.IO) {
            return@withContext userDataRepository.insertUser(user)
        }

    fun getUsers() {
        CoroutineScope(Dispatchers.Main).launch {
            val idLong = getUserList()
            allUserList.value = idLong
        }
    }

    private suspend fun getUserList(): List<User> =
        withContext(Dispatchers.IO) {
            return@withContext userDataRepository.getAllUser()
        }

}