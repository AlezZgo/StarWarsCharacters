package com.example.starwarscharacters.data.datasource

import com.example.starwarscharacters.data.network.model.CharacterDto
import com.example.starwarscharacters.data.network.model.CharacterHalfListDto

interface RemoteDataSource {
    suspend fun getAllCharacters() : List<CharacterDto>
}