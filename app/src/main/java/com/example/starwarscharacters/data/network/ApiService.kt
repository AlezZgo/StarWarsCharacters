package com.example.starwarscharacters.data.network

import com.example.starwarscharacters.data.network.model.CharacterHalfListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/people")
    suspend fun getHalfOfCharacters(): CharacterHalfListDto

    @GET("{url}")
    suspend fun getHalfOfCharactersByUrl(@Path("url")url: String): CharacterHalfListDto

}