package com.example.mireakotlincompose.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_facts")
data class CatFactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fact: String
)