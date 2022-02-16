package com.example.starwarscharacters.data.datasource

import com.example.starwarscharacters.data.network.ApiService
import com.example.starwarscharacters.data.network.model.CharacterCloud
import com.example.starwarscharacters.data.network.model.FilmCloud
import com.example.starwarscharacters.data.network.model.HomeWorldCloud
import javax.inject.Inject

class BaseRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {
    //todo get base url to class params
    override suspend fun getAllCharacters(): List<CharacterCloud> {
        val results = mutableListOf<CharacterCloud>()
        try {
            var half = apiService.getPartOfCharacters()
            results.addAll(half.results)
            while (half.next != null) {
                half =
                    apiService.getPartOfCharactersByUrl(half.next!!)
                results.addAll(half.results)
            }

        } catch (e: Exception) {
            throw RuntimeException("Can`t get All Characters")
        }
        return results.toList()

    }

    override suspend fun getCharacterFilm(url: String): FilmCloud {
        return apiService.getCharacterFilm(url)
    }

    override suspend fun getCharacterHomeWorld(url: String): HomeWorldCloud {
        return apiService.getCharacterHomeWorld(url)
    }
}