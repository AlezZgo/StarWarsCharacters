package com.example.starwarscharacters.presentation

import android.app.Application
import com.example.starwarscharacters.di.DaggerApplicationComponent

class StarWarsApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

}