package com.tembhode.myapplicationone.models

import androidx.room.Entity

/**
 * Created by Pankaj Gadge on 10/14/2021.
 */
@Entity(tableName = "user_table")
data class User(var name: String, var mobile: String, var book: String)