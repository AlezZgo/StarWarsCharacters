package com.example.starwarscharacters.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.datasource.LocalDataSourceImpl
import com.example.starwarscharacters.data.mapper.CharacterMapper
import com.example.starwarscharacters.data.network.ApiFactory
import kotlinx.coroutines.delay
import java.lang.Exception

class RefreshDataWorker(context: Context, private val workerParameters: WorkerParameters) :
    CoroutineWorker(
        context,
        workerParameters
    ) {

    private val apiService = ApiFactory.apiService
    private val characterDao = AppDatabase.getInstance(context).characterDao()
    private val mapper = CharacterMapper()

    override suspend fun doWork(): Result {
        while (true){
            try {

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