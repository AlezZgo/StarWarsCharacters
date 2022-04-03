package com.example.starwarscharacters.data.datasource

import androidx.lifecycle.LiveData
import com.example.starwarscharacters.data.database.CharacterInfoDb

interface LocalDataSource {

    suspend fun cacheIsEmpty(): Boolean

    suspend fun insertList(list: List<CharacterInfoDb>)

    suspend fun insert(character: CharacterInfoDb)

    fun getCharacters(filter: String): LiveData<List<CharacterInfoDb>>

    fun getCharacter(name: String): CharacterInfoDb?

    fun getCharacterLiveData(name: String): LiveData<CharacterInfoDb>

    fun getFavouriteCharacters(): LiveData<List<CharacterInfoDb>>


}