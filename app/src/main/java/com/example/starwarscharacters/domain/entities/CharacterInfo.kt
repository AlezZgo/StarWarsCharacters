package com.example.starwarscharacters.domain.entities

data class CharacterInfo(
    val name: String,
    val gender: String,
    val mass: String,
    val height: String,
    val homeWorld: String,
    val films: String,
    var isFavourite: Boolean
)
