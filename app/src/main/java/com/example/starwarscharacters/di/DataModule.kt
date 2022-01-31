package com.example.starwarscharacters.di

import android.app.Application
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.database.CharacterDao
import com.example.starwarscharacters.data.network.ApiFactory
import com.example.starwarscharacters.data.network.ApiService
import com.example.starwarscharacters.data.repository.CharactersRepositoryImpl
import com.example.starwarscharacters.domain.repositories.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: CharactersRepositoryImpl): CharactersRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideCharacterListDao(
            application: Application
        ): CharacterDao {
            return AppDatabase.getInstance(application).characterDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}