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
        films = infoDbModel.films
    )

    fun mapDtoToDbModel(characterDto: CharacterDto) = CharacterInfoDbModel(
        name = characterDto.name,
        gender = characterDto.gender,
        mass = characterDto.mass.toString(),
        height = characterDto.height.toString(),
        homeWorld = characterDto.homeworld,
        films = characterDto.films.joinToString(separator = ",")
    )
}