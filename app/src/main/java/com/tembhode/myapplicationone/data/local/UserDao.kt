package com.tembhode.myapplicationone.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tembhode.myapplicationone.models.User

/**
 * Created by Pankaj Gadge on 10/14/2021.
 */
@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("select * from user_table")
    fun getUsersList(): List<User>
}