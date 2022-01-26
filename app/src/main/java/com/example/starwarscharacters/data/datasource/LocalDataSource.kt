package com.example.starwarscharacters.data.datasource

import androidx.lifecycle.LiveData

interface LocalDataSource {
    fun getFavourites()

    fun getCharacters()

    fun getCharacter(name: String)


}