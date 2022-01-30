package com.example.starwarscharacters.data.datasource

import androidx.lifecycle.LiveData
import com.example.starwarscharacters.data.database.CharacterInfoDbModel

interface LocalDataSource {

    suspend fun insertList(list: List<CharacterInfoDbModel>)

    suspend fun insertCharacter(character: CharacterInfoDbModel)

    fun getCharacters(filter: String): LiveData<List<CharacterInfoDbModel>>

    fun getCharacter(name: String): CharacterInfoDbModel

    fun getFavouritesCharacters(): LiveData<List<CharacterInfoDbModel>>

}