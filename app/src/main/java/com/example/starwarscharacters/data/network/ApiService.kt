package com.example.starwarscharacters.data.network

import com.example.starwarscharacters.data.network.model.FilmCloud
import com.example.starwarscharacters.data.network.model.HomeWorldCloud
import com.example.starwarscharacters.data.network.model.PartOfCharactersCloud
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    @GET("api/people")
    suspend fun getPartOfCharacters(): PartOfCharactersCloud

    @GET
    suspend fun getPartOfCharactersByUrl(@Url url: String): PartOfCharactersCloud

    @GET
    suspend fun getCharacterFilm(@Url url: String): FilmCloud

    @GET
    suspend fun getCharacterHomeWorld(@Url url: String): HomeWorldCloud

}