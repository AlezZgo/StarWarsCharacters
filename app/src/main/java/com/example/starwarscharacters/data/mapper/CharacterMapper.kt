package com.example.starwarscharacters.data.mapper

import com.example.starwarscharacters.data.database.CharacterInfoDbModel
import com.example.starwarscharacters.data.network.ApiFactory
import com.example.starwarscharacters.data.network.ApiService
import com.example.starwarscharacters.data.network.model.CharacterCloud
import com.example.starwarscharacters.domain.entities.CharacterInfo
import javax.inject.Inject

class CharacterMapper @Inject constructor(private val apiService: ApiService) {

    fun mapDbModelToEntity(infoDbModel: CharacterInfoDbModel) = CharacterInfo(
        name = infoDbModel.name,
        gender = infoDbModel.gender,
        mass = infoDbModel.mass,
        height = infoDbModel.height,
        homeWorld = infoDbModel.homeWorld,
        films = infoDbModel.films,
        isFavourite = infoDbModel.isFavourite
    )

    suspend fun mapDtoToDbModel(characterCloud: CharacterCloud, isFavourite: Boolean) =
        CharacterInfoDbModel(
            name = characterCloud.name,
            gender = characterCloud.gender,
            mass = characterCloud.mass.toString(),
            height = characterCloud.height.toString(),
            homeWorld = apiService.getCharacterHomeWorld(
                characterCloud.homeworld.removePrefix(ApiFactory.BASE_URL)).name,
            films = characterCloud.films.map {
                apiService.getCharacterFilm(
                    (it.removePrefix(ApiFactory.BASE_URL))).title
            }.joinToString(separator = ","),
            isFavourite = isFavourite
        )

    fun mapEntityToDbModel(CharacterInfo: CharacterInfo) = CharacterInfoDbModel(
        name = CharacterInfo.name,
        gender = CharacterInfo.gender,
        mass = CharacterInfo.mass,
        height = CharacterInfo.height,
        homeWorld = CharacterInfo.homeWorld,
        films = CharacterInfo.films,
        isFavourite = CharacterInfo.isFavourite
    )
}