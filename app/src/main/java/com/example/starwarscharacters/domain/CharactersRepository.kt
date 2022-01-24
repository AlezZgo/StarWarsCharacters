package com.example.starwarscharacters.domain

import androidx.lifecycle.LiveData

interface CharactersRepository {
    fun getCharacter(name:String): LiveData<Character>

    fun getCharacterList(filter: String): LiveData<List<Character>>
}