package com.example.starwarscharacters.data.datasource

import com.example.starwarscharacters.data.network.ApiFactory
import com.example.starwarscharacters.data.network.ApiService
import com.example.starwarscharacters.data.network.model.CharacterCloud
import java.lang.RuntimeException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {
    //todo get base url to class params
    override suspend fun getAllCharacters(): List<CharacterCloud> {
        val results = mutableListOf<CharacterCloud>()
        try {
            var half = apiService.getHalfOfCharacters()
            results.addAll(half.results)
            while (half.next != null) {
                half =
                    apiService.getHalfOfCharactersByUrl(half.next!!.removePrefix(ApiFactory.BASE_URL))
                results.addAll(half.results)
            }

        }catch (e: Exception){
            throw RuntimeException("Cannot get info from cloud")
        }
        return results.toList()

    }
}