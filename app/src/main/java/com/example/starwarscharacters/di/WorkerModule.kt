package com.example.starwarscharacters.di

import com.example.starwarscharacters.data.workers.ChildWorkerFactory
import com.example.starwarscharacters.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}