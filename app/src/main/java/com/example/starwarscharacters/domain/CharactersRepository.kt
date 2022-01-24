package com.example.starwarscharacters.domain

import androidx.lifecycle.LiveData

interface CharactersRepository {
    fun getCharacter(name:String): LiveData<CharacterInfo>

    fun getCharacterList(): LiveData<List<CharacterInfo>>
}