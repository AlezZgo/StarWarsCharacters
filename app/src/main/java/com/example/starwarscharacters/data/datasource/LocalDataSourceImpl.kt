package com.example.starwarscharacters.data.datasource

import androidx.lifecycle.LiveData
import com.example.starwarscharacters.data.database.CharacterDao
import com.example.starwarscharacters.data.database.CharacterInfoDbModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
) : LocalDataSource {

    override suspend fun insertList(list: List<CharacterInfoDbModel>) {
        characterDao.insertList(list)
    }

    override suspend fun insert(character: CharacterInfoDbModel) {
        characterDao.insert(character)
    }

    override fun getCharacters(filter: String): LiveData<List<CharacterInfoDbModel>> {
        return characterDao.getCharactersList(filter)
    }

    override fun getCharacter(name: String): LiveData<CharacterInfoDbModel> {
        return characterDao.getCharacter(name)
    }

    override fun getFavouritesCharacters(): LiveData<List<CharacterInfoDbModel>> {
        return characterDao.getFavouritesCharacters()
    }
}