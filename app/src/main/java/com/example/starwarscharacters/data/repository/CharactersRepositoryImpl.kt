package com.example.starwarscharacters.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.database.CharacterDao
import com.example.starwarscharacters.domain.Character
import com.example.starwarscharacters.domain.CharactersRepository

class CharactersRepositoryImpl(application: Application) : CharactersRepository {

    private val characterDao = AppDatabase.getInstance(application).characterDao()


    override fun getCharacter(name: String): LiveData<Character> {
        characterDao.getCharacter(name)
    }

    override fun getCharacterList(): LiveData<List<Character>> {
        characterDao.getCharacterList()
    }
}