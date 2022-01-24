package com.example.starwarscharacters.data.mapper

import com.example.starwarscharacters.data.database.CharacterInfoDbModel
import com.example.starwarscharacters.domain.CharacterInfo

class CharacterMapper {
    fun mapDbModelToEntity(infoDbModel: CharacterInfoDbModel) = CharacterInfo(
        infoDbModel.name,
        infoDbModel.gender,
        infoDbModel.mass,
        infoDbModel.height,
        infoDbModel.homeWorld,
        infoDbModel.films
    )
}