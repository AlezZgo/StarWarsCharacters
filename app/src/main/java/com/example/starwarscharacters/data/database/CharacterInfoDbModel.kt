package com.example.starwarscharacters.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterInfoDbModel(
    @PrimaryKey
    val name: String,
    val gender: String,
    val mass: String,
    val height: String,
    val homeWorld: String,
    val films: List<String>
)