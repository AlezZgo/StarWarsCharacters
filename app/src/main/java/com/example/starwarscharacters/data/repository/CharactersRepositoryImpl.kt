package com.example.starwarscharacters.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.mapper.CharacterMapper
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.repositories.CharactersRepository

class CharactersRepositoryImpl(application: Application) : CharactersRepository {

    private val characterDao = AppDatabase.getInstance(application).characterDao()

    private val mapper = CharacterMapper()

    override fun getCharacter(name: String): LiveData<CharacterInfo> {
        return Transformations.map(characterDao.getCharacter(name)){
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun getCharacterList(): LiveData<List<CharacterInfo>> {
        Log.i("Logi","${characterDao.getCharacterList().value?.size}")
        val deb = Transformations.map(characterDao.getCharacterList()){list->

            list.map{
                mapper.mapDbModelToEntity(it)
            }
        }
        Log.i("Login","start ${deb.value?.size}")
        return deb
    }

}