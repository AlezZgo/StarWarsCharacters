package com.example.starwarscharacters.data.network

import com.example.starwarscharacters.data.network.model.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/people")
    suspend fun getCharacters(): CharacterListDto

}