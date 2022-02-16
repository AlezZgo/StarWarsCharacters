package com.example.starwarscharacters.di.module

import android.app.Application
import com.example.starwarscharacters.data.database.AppDatabase
import com.example.starwarscharacters.data.database.CharacterDao
import com.example.starwarscharacters.data.datasource.LocalDataSource
import com.example.starwarscharacters.data.datasource.BaseLocalDataSource
import com.example.starwarscharacters.data.datasource.RemoteDataSource
import com.example.starwarscharacters.data.datasource.BaseRemoteDataSource
import com.example.starwarscharacters.data.mapper.CharacterMapper
import com.example.starwarscharacters.data.repository.BaseCharactersRepository
import com.example.starwarscharacters.di.ApplicationScope
import com.example.starwarscharacters.domain.repositories.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: BaseCharactersRepository): CharactersRepository

    @ApplicationScope
    @Binds
    fun bindRemoteDataSource(impl: BaseRemoteDataSource): RemoteDataSource

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
            return BaseLocalDataSource(AppDatabase.instance(application).characterDao())
        }

        @ApplicationScope
        @Provides
        fun provideCharacterMapper() = CharacterMapper()

    }
}