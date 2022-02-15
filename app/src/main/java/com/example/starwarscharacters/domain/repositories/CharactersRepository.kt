package com.example.starwarscharacters.domain.repositories

import androidx.lifecycle.LiveData
import com.example.starwarscharacters.domain.entities.CharacterInfo

interface CharactersRepository {
    fun character(name: String): CharacterInfo

    fun characterLiveData(name: String): LiveData<CharacterInfo>

    fun characterList(filter: String): LiveData<List<CharacterInfo>>

    fun favouritesCharacters(): LiveData<List<CharacterInfo>>

    suspend fun insert(character: CharacterInfo)

    suspend fun refreshData()

}