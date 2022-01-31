package com.example.starwarscharacters.di

import android.app.Application
import com.example.starwarscharacters.presentation.MainActivity
import com.example.starwarscharacters.presentation.StarWarsApp
import com.example.starwarscharacters.presentation.characters.CharactersFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: CharactersFragment)

    fun inject(application: StarWarsApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
