package com.example.starwarscharacters.data.datasource

import com.example.starwarscharacters.data.network.ApiFactory
import com.example.starwarscharacters.data.network.ApiService
import com.example.starwarscharacters.data.network.model.CharacterDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) : RemoteDataSource {

    override suspend fun getAllCharacters(): List<CharacterDto> {
        val results = mutableListOf<CharacterDto>()
        var half = apiService.getHalfOfCharacters()

        results.addAll(half.results)
        while (half.next != null) {
            half =
                apiService.getHalfOfCharactersByUrl(half.next!!.removePrefix(ApiFactory.BASE_URL))
            results.addAll(half.results)
        }
        return results.toList()
    }

}