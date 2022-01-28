package com.example.starwarscharacters.data.datasource

import com.example.starwarscharacters.data.network.ApiFactory
import com.example.starwarscharacters.data.network.model.CharacterDto

class RemoteDataSourceImpl : RemoteDataSource {

    private val apiService = ApiFactory.apiService

    override suspend fun getAllCharacters(): List<CharacterDto> {
        val results = mutableListOf<CharacterDto>()
        var half = apiService.getHalfOfCharacters()

        results.addAll(half.results)
        while (half.next != null) {
            half = apiService.getHalfOfCharactersByUrl(half.next!!)
            results.addAll(half.results)
        }
        return results.toList()
    }

}