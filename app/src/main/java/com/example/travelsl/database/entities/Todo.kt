package com.example.travelsl.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    var item: String? // the name of the todos item
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null // the unique ID of the todos item in the database
}
