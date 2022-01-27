package com.example.starwarscharacters.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.datasource.LocalDataSourceImpl
import com.example.starwarscharacters.data.datasource.RemoteDataSourceImpl
import com.example.starwarscharacters.data.mapper.CharacterMapper
import com.example.starwarscharacters.data.network.ApiFactory
import kotlinx.coroutines.delay
import java.lang.Exception

class RefreshDataWorker(context: Context, private val workerParameters: WorkerParameters) :
    CoroutineWorker(
        context,
        workerParameters
    ) {

    private val remoteDataSourceImpl = RemoteDataSourceImpl()
    private val characterDao = AppDatabase.getInstance(context).characterDao()
    private val mapper = CharacterMapper()

    override suspend fun doWork(): Result {
        while (true){
            try {
                remoteDataSourceImpl.getAllCharacters().forEach {newCharacterDto->
                    val oldCharacterDbModel = characterDao.getCharacter(newCharacterDto.name)
                    characterDao.insert(mapper.mapDtoToDbModel(
                        newCharacterDto,
                        isFavourite = oldCharacterDbModel.value?.isFavourite ?: false
                    ))
                }
            }catch (e: Exception){

            }
            delay(UPDATE_TIME_IN_SECONDS * MILLIS_IN_SEC)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"
        const val UPDATE_TIME_IN_SECONDS = 10
        const val MILLIS_IN_SEC = 1000L

        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}