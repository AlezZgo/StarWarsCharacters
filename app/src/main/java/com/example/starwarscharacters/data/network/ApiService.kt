package com.example.starwarscharacters.data.network

import com.example.starwarscharacters.data.network.model.CharacterDto
import com.example.starwarscharacters.data.network.model.CharacterListDto
import retrofit2.http.GET

interface ApiService {

    @GET("api/people")
    suspend fun getCharacters(): CharacterListDto

}