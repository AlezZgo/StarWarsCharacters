package com.example.starwarscharacters.data.datasource

import androidx.lifecycle.LiveData
import com.example.starwarscharacters.data.database.CharacterDao
import com.example.starwarscharacters.data.database.CharacterInfoDb
import javax.inject.Inject

class BaseLocalDataSource @Inject constructor(
    private val characterDao: CharacterDao,
) : LocalDataSource {
    override suspend fun cacheIsEmpty(): Boolean {
        return characterDao.count() <= 0
    }

    override suspend fun insertList(list: List<CharacterInfoDb>) {
        characterDao.insertList(list)
    }

    override suspend fun insert(character: CharacterInfoDb) {
        characterDao.insert(character)
    }

    override fun getCharacters(filter: String): LiveData<List<CharacterInfoDb>> {
        return characterDao.getCharactersList(filter)
    }

    override fun getCharacter(name: String): CharacterInfoDb? {
        return characterDao.getCharacter(name)
    }

    override fun getCharacterLiveData(name: String): LiveData<CharacterInfoDb> {
        return characterDao.getCharacterLiveData(name)
    }

    override fun getFavouriteCharacters(): LiveData<List<CharacterInfoDb>> {
        return characterDao.getFavouritesCharacters()
    }
}