package com.example.starwarscharacters.domain.repositories

import androidx.lifecycle.LiveData
import com.example.starwarscharacters.domain.entities.CharacterInfo

interface CharactersRepository {
    fun getCharacter(name: String): CharacterInfo

    fun getCharacterList(filter: String): LiveData<List<CharacterInfo>>

    fun getFavouritesCharacters(): LiveData<List<CharacterInfo>>

    suspend fun insert(character: CharacterInfo)

    fun loadData()


}