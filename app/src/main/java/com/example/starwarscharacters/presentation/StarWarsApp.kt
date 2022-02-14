package com.example.starwarscharacters.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.starwarscharacters.data.workers.CharacterWorkerFactory
import com.example.starwarscharacters.di.DaggerApplicationComponent
import javax.inject.Inject

class StarWarsApp : Application(), Configuration.Provider {

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