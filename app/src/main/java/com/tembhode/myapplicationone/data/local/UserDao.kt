package com.tembhode.myapplicationone.data.local

import androidx.room.*
import com.tembhode.myapplicationone.models.User

/**
 * Created by Pankaj Gadge on 10/14/2021.
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User):Long

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("select * from user_table ORDER BY id DESC")
    fun getUsersList(): List<User>
}