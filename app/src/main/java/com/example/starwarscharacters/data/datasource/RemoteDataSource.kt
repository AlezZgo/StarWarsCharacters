package com.example.starwarscharacters.data.datasource

import com.example.starwarscharacters.data.network.model.CharacterDto

interface RemoteDataSource {
    suspend fun getAllCharacters(): List<CharacterDto>
}