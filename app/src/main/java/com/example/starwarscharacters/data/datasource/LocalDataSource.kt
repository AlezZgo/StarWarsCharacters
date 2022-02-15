package com.example.starwarscharacters.data.datasource

import androidx.lifecycle.LiveData
import com.example.starwarscharacters.data.database.CharacterInfoDb

interface LocalDataSource {

    suspend fun insertList(list: List<CharacterInfoDb>)

    suspend fun insert(character: CharacterInfoDb)

    fun getCharacters(filter: String): LiveData<List<CharacterInfoDb>>

    fun getCharacter(name: String): CharacterInfoDb

    fun getCharacterLiveData(name: String) : LiveData<CharacterInfoDb>

    fun getFavouritesCharacters(): LiveData<List<CharacterInfoDb>>


}