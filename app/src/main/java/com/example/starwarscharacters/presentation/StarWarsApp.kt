package com.example.starwarscharacters.presentation

import android.app.Application
import android.content.res.Configuration
import com.example.starwarscharacters.data.workers.CharacterWorkerFactory
import javax.inject.Inject

class StarWarsApp: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CharacterWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}