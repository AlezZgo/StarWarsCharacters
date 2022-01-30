package com.example.starwarscharacters.data.datasource

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.database.CharacterInfoDbModel

class LocalDataSourceImpl(application: Application) : LocalDataSource {

    private val characterDao = AppDatabase.getInstance(application).characterDao()
    override suspend fun insertList(list: List<CharacterInfoDbModel>) {
        characterDao.insertList(list)
    }

    override suspend fun insertCharacter(character: CharacterInfoDbModel) {
        characterDao.insert(character)
    }

    override fun getCharacters(filter: String): LiveData<List<CharacterInfoDbModel>> {
        return characterDao.getCharactersList(filter)
    }

    override fun getCharacter(name: String): CharacterInfoDbModel {
        return characterDao.getCharacter(name)
    }

    override fun getFavouritesCharacters(): LiveData<List<CharacterInfoDbModel>> {
        return characterDao.getFavouritesCharacters()
    }
}