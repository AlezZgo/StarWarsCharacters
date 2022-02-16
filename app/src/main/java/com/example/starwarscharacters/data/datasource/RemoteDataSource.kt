package com.example.starwarscharacters.data.datasource

import com.example.starwarscharacters.data.network.model.CharacterCloud
import com.example.starwarscharacters.data.network.model.FilmCloud
import com.example.starwarscharacters.data.network.model.HomeWorldCloud
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteDataSource {
    suspend fun getAllCharacters(): List<CharacterCloud>

    suspend fun getCharacterFilm(@Path("url") url: String): FilmCloud

    suspend fun getCharacterHomeWorld(@Path("url") url: String): HomeWorldCloud
}