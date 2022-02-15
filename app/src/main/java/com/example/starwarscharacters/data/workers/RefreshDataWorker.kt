package com.example.starwarscharacters.data.workers

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.work.*
import com.example.starwarscharacters.data.database.CharacterDao
import com.example.starwarscharacters.data.database.CharacterInfoDb
import com.example.starwarscharacters.data.datasource.RemoteDataSource
import com.example.starwarscharacters.data.mapper.CharacterMapper
import javax.inject.Inject

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val remoteDataSource: RemoteDataSource,
    private val characterDao: CharacterDao,
    private val mapper: CharacterMapper,
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        remoteDataSource.getAllCharacters().forEach { newCharacterDto ->
            val oldCharacterDb: LiveData<CharacterInfoDb> =
                characterDao.getCharacter(newCharacterDto.name)
            val newCharacterDbModel = mapper.mapDtoToDbModel(
                newCharacterDto, isFavourite = oldCharacterDb.value!!.isFavourite ?: false)
//            if (oldCharacterDbModel != newCharacterDbModel) {
//                characterDao.insert(newCharacterDbModel)
//            }
        }
        return Result.success()
    }

    companion object {
        const val NAME = "RefreshDataWorker"

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }

    class Factory @Inject constructor(
        private val characterDao: CharacterDao,
        private val remoteDataSource: RemoteDataSource,
        private val mapper: CharacterMapper,
    ) : ChildWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters,
        ): ListenableWorker {
            return RefreshDataWorker(
                context,
                workerParameters,
                remoteDataSource,
                characterDao,
                mapper
            )
        }
    }
}