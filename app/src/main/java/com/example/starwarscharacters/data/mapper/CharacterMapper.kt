package com.example.starwarscharacters.data.mapper

import com.example.starwarscharacters.data.database.CharacterInfoDbModel
import com.example.starwarscharacters.data.network.model.CharacterDto
import com.example.starwarscharacters.domain.entities.CharacterInfo

class CharacterMapper {
    fun mapDbModelToEntity(infoDbModel: CharacterInfoDbModel) = CharacterInfo(
        name = infoDbModel.name,
        gender = infoDbModel.gender,
        mass = infoDbModel.mass,
        height = infoDbModel.height,
        homeWorld = infoDbModel.homeWorld,
        films = infoDbModel.films,
        isFavourite = infoDbModel.isFavourite
    )

    fun mapDtoToDbModel(characterDto: CharacterDto, isFavourite: Boolean) = CharacterInfoDbModel(
        name = characterDto.name,
        gender = characterDto.gender,
        mass = characterDto.mass.toString(),
        height = characterDto.height.toString(),
        homeWorld = characterDto.homeworld,
        films = characterDto.films.map { it.title }.joinToString(separator = ","),
        isFavourite = false
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