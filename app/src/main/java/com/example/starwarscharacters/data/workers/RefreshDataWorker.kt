package com.example.starwarscharacters.data.workers

import android.content.Context
import androidx.work.*
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.database.CharacterInfoDbModel
import com.example.starwarscharacters.data.datasource.RemoteDataSourceImpl
import com.example.starwarscharacters.data.mapper.CharacterMapper
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class RefreshDataWorker(context: Context, private val workerParameters: WorkerParameters) :
    CoroutineWorker(
        context,
        workerParameters
    ) {

    private val remoteDataSourceImpl = RemoteDataSourceImpl()
    private val characterDao = AppDatabase.getInstance(context).characterDao()
    private val mapper = CharacterMapper()

    override suspend fun doWork(): Result {

        remoteDataSourceImpl.getAllCharacters().forEach { newCharacterDto ->
            val oldCharacterDbModel: CharacterInfoDbModel? =
                characterDao.getCharacter(newCharacterDto.name)
            val newCharacterDbModel = mapper.mapDtoToDbModel(
                newCharacterDto, isFavourite = oldCharacterDbModel?.isFavourite ?: false)
            if (oldCharacterDbModel != newCharacterDbModel) {
                characterDao.insert(newCharacterDbModel)
            }
        }
        return Result.success()
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