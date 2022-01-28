package com.example.starwarscharacters.data.network

import com.example.starwarscharacters.data.network.model.CharacterHalfListDto
import com.example.starwarscharacters.data.network.model.FilmDto
import com.example.starwarscharacters.data.network.model.HomeWorldDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {

    @GET("api/people")
    suspend fun getHalfOfCharacters(): CharacterHalfListDto

    @GET
    suspend fun getHalfOfCharactersByUrl(@Url url:String ): CharacterHalfListDto

    @GET("{url}")
    suspend fun getCharacterFilm(@Path("url") url: String): FilmDto

    @GET("{url}")
    suspend fun getCharacterHomeWorld(@Path("url") url: String): HomeWorldDto

}