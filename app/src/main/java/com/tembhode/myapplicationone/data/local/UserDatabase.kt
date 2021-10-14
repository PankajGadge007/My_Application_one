package com.tembhode.myapplicationone.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tembhode.myapplicationone.models.User

/**
 * Created by Pankaj Gadge on 10/14/2021.
 */
@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        var instance: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempObj = instance
            if (tempObj != null) {
                return tempObj
            }

            val tempInstance =
                Room.databaseBuilder(context, UserDatabase::class.java, "user_database").build()
            instance = tempInstance
            return tempInstance
        }
    }
}