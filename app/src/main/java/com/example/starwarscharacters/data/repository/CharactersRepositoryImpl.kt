package com.example.starwarscharacters.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.starwarscharacters.data.datasource.LocalDataSource
import com.example.starwarscharacters.data.mapper.CharacterMapper
import com.example.starwarscharacters.data.workers.RefreshDataWorker
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.repositories.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val mapper: CharacterMapper,
    private val localDataSource: LocalDataSource,
    private val application: Application,
) : CharactersRepository {

    override fun character(name: String): LiveData<CharacterInfo> {
        return Transformations.map(localDataSource.getCharacter(name)){
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun characterList(filter: String): LiveData<List<CharacterInfo>> {
        return Transformations.map(localDataSource.getCharacters(filter)) { list ->
            list.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun favouritesCharacters(): LiveData<List<CharacterInfo>> {
        return Transformations.map(localDataSource.getFavouritesCharacters()) { list ->
            list.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun insert(character: CharacterInfo) {
        localDataSource.insert(mapper.mapEntityToDbModel(character))
    }

    override fun loadData() {
        WorkManager.getInstance(application)
            .enqueueUniqueWork(
                RefreshDataWorker.NAME,
                ExistingWorkPolicy.REPLACE,
                RefreshDataWorker.makeRequest()
            )
    }

}