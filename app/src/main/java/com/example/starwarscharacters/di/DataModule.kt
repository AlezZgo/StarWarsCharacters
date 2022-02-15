package com.example.starwarscharacters.di

import android.app.Application
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.database.CharacterDao
import com.example.starwarscharacters.data.datasource.LocalDataSource
import com.example.starwarscharacters.data.datasource.LocalDataSourceImpl
import com.example.starwarscharacters.data.datasource.RemoteDataSource
import com.example.starwarscharacters.data.datasource.RemoteDataSourceImpl
import com.example.starwarscharacters.data.mapper.CharacterMapper
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

    @ApplicationScope
    @Binds
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    companion object {

        @ApplicationScope
        @Provides
        fun provideCharacterListDao(
            application: Application,
        ): CharacterDao {
            return AppDatabase.instance(application).characterDao()
        }

        @ApplicationScope
        @Provides
        fun provideLocalDataSource(
            application: Application,
        ): LocalDataSource {
            return LocalDataSourceImpl(AppDatabase.instance(application).characterDao())
        }

        @ApplicationScope
        @Provides
        fun provideCharacterMapper() = CharacterMapper()

    }
}