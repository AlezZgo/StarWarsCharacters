package com.example.starwarscharacters.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.starwarscharacters.data.datasource.LocalDataSourceImpl
import com.example.starwarscharacters.data.mapper.CharacterMapper
import com.example.starwarscharacters.data.workers.RefreshDataWorker
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.repositories.CharactersRepository

class CharactersRepositoryImpl(private val application: Application) : CharactersRepository {

    private val localDataSource = LocalDataSourceImpl(application)
    private val mapper = CharacterMapper()

    override fun getCharacter(name: String): CharacterInfo {
        return mapper.mapDbModelToEntity(localDataSource.getCharacter(name))

    }

    override fun getCharacterList(filter: String): LiveData<List<CharacterInfo>> {
        return Transformations.map(localDataSource.getCharacters(filter)) { list ->
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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }

}