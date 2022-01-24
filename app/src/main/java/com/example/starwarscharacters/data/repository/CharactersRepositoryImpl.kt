package com.example.starwarscharacters.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.mapper.CharacterMapper
import com.example.starwarscharacters.domain.CharacterInfo
import com.example.starwarscharacters.domain.CharactersRepository

class CharactersRepositoryImpl(application: Application) : CharactersRepository {

    private val characterDao = AppDatabase.getInstance(application).characterDao()
    private val mapper = CharacterMapper()

    override fun getCharacter(name: String): LiveData<CharacterInfo> {
        return Transformations.map(characterDao.getCharacter(name)){
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun getCharacterList(): LiveData<List<CharacterInfo>> {
        return Transformations.map(characterDao.getCharacterList()){list->
            list.map{
                mapper.mapDbModelToEntity(it)
            }
        }
    }
}