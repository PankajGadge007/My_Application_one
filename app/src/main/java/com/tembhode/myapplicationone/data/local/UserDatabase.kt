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

        @Volatile
        var instance: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            // thread-safe
            return instance ?: synchronized(this) {
                // if instance is not null, then return it,
                // if it is null, then create the database instance
                val tempObj = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                instance = tempObj
                // return instance
                tempObj
            }
        }
    }
}