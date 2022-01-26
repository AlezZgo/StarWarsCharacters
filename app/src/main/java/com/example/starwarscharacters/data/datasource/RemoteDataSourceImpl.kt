package com.example.starwarscharacters.data.datasource

import com.example.starwarscharacters.data.network.ApiFactory
import com.example.starwarscharacters.data.network.model.CharacterDto
import com.example.starwarscharacters.data.network.model.CharacterHalfListDto

class RemoteDataSourceImpl : RemoteDataSource {

    private val apiService = ApiFactory.apiService

    override suspend fun getAllCharacters(): List<CharacterDto> {
        val results = mutableListOf<CharacterDto>()
        var half = apiService.getHalfOfCharacters()

        results.addAll(half.results)
        while (half.next != NULL) {
            half = apiService.getHalfOfCharactersByUrl(half.next)
            results.addAll(half.results)
        }
        return results.toList()
    }

    companion object {
        private const val NULL = "null"
    }


}