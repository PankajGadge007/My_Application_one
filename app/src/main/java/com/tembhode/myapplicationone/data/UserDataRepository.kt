package com.tembhode.myapplicationone.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.tembhode.myapplicationone.data.local.UserDao
import com.tembhode.myapplicationone.data.local.UserDatabase
import com.tembhode.myapplicationone.models.User

/**
 * Created by Pankaj Gadge on 10/14/2021.
 */
class UserDataRepository constructor(application: Context) {
    private var userDao: UserDao
    private var database: UserDatabase

    init {
        database = UserDatabase.getDatabase(application)
        userDao = database.userDao()
    }

    fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }

    fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    fun getAllUser(): List<User> {
        return userDao.getUsersList()
    }
}