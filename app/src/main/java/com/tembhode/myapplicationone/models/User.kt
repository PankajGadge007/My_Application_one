package com.tembhode.myapplicationone.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Created by Pankaj Gadge on 10/14/2021.
 */
@Entity(tableName = "user_table")
data class User(var name: String, var mobile: String, var book: String)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}