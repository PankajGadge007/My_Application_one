package com.tembhode.myapplicationone.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tembhode.myapplicationone.data.UserDataRepository

/**
 * Created by Pankaj Gadge on 10/14/2021.
 */
class MyVMFactory(private val userDataRepository: UserDataRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(userDataRepository) as T
        } else if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(userDataRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}