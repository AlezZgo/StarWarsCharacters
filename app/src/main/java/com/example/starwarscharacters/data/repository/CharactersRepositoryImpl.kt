package com.example.starwarscharacters.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.starwarscharacters.data.database.CharacterInfoDbModel
import com.example.starwarscharacters.data.datasource.LocalDataSourceImpl
import com.example.starwarscharacters.data.datasource.RemoteDataSourceImpl
import com.example.starwarscharacters.data.mapper.CharacterMapper
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.repositories.CharactersRepository

class CharactersRepositoryImpl(application: Application) : CharactersRepository {


    private val remoteDataSource = RemoteDataSourceImpl()
    private val localDataSource = LocalDataSourceImpl(application)

    private val mapper = CharacterMapper()

    override fun getCharacter(name: String): LiveData<CharacterInfo> {
        return Transformations.map(localDataSource.getCharacter(name)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun getCharacterList(): LiveData<List<CharacterInfo>> {
        return Transformations.map(localDataSource.getCharacters()) { list ->
            list.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getFavouritesCharacters(): LiveData<List<CharacterInfo>> {
        return Transformations.map(localDataSource.getFavouritesCharacters()) { list ->
            list.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun insert(character: CharacterInfo) {
        localDataSource.insertCharacter(mapper.mapEntityToDbModel(character))
    }

}