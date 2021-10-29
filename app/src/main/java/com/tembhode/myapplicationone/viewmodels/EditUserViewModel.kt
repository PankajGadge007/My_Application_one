package com.tembhode.myapplicationone.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tembhode.myapplicationone.data.UserDataRepository
import com.tembhode.myapplicationone.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditUserViewModel(var userDataRepository: UserDataRepository) : ViewModel() {

    var isUpdatDone = MutableLiveData<Boolean>()

    fun updateData(user: User) {
        CoroutineScope(Dispatchers.Main).launch {
//            userDataRepository.updateUser(user)
            updateUser(user)
            isUpdatDone.value = true
        }
    }

    private suspend fun updateUser(user: User) =
        withContext(Dispatchers.IO) {
            userDataRepository.updateUser(user)
        }
}