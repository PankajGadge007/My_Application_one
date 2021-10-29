package com.tembhode.myapplicationone.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Pankaj Gadge on 10/14/2021.
 */
@Entity(tableName = "user_table")
class User() : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

    @ColumnInfo(name = "name")
    var name: String? = null

    @ColumnInfo(name = "mobile")
    var mobile: String? = null

    @ColumnInfo(name = "book")
    var book: String? = null
}
