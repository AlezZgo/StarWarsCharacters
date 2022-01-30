package com.example.starwarscharacters.data.mapper

import com.example.starwarscharacters.data.database.CharacterInfoDbModel
import com.example.starwarscharacters.data.network.ApiFactory
import com.example.starwarscharacters.data.network.model.CharacterDto
import com.example.starwarscharacters.domain.entities.CharacterInfo

class CharacterMapper {

    val api = ApiFactory.apiService

    fun mapDbModelToEntity(infoDbModel: CharacterInfoDbModel) = CharacterInfo(
        name = infoDbModel.name,
        gender = infoDbModel.gender,
        mass = infoDbModel.mass,
        height = infoDbModel.height,
        homeWorld = infoDbModel.homeWorld,
        films = infoDbModel.films,
        isFavourite = infoDbModel.isFavourite
    )

    suspend fun mapDtoToDbModel(characterDto: CharacterDto, isFavourite: Boolean) =
        CharacterInfoDbModel(
            name = characterDto.name,
            gender = characterDto.gender,
            mass = characterDto.mass.toString(),
            height = characterDto.height.toString(),
            homeWorld = api.getCharacterHomeWorld(
                characterDto.homeworld.removePrefix(ApiFactory.BASE_URL)).name,
            films = characterDto.films.map {
                api.getCharacterFilm(
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