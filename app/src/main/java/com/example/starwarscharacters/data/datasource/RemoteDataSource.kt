package com.example.starwarscharacters.data.datasource

import com.example.starwarscharacters.data.network.model.CharacterCloud

interface RemoteDataSource {
    suspend fun getAllCharacters(): List<CharacterCloud>
}