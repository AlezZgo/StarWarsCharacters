package com.example.starwarscharacters.data.mapper

import com.example.starwarscharacters.data.database.CharacterInfoDb
import com.example.starwarscharacters.data.network.model.CharacterCloud
import com.example.starwarscharacters.domain.entities.CharacterInfo

class CharacterMapper {

    fun mapDbModelToEntity(infoDb: CharacterInfoDb) = CharacterInfo(
        name = infoDb.name,
        gender = infoDb.gender,
        mass = infoDb.mass,
        height = infoDb.height,
        homeWorld = infoDb.homeWorld,
        films = infoDb.films,
        isFavourite = infoDb.isFavourite
    )

    fun mapCloudToDbModel(
        characterCloud: CharacterCloud,
        isFavourite: Boolean,
        homeWorld: String,
        films: String,
    ) = CharacterInfoDb(
            name = characterCloud.name,
            gender = characterCloud.gender,
            mass = characterCloud.mass,
            height = characterCloud.height,
            homeWorld = "",
            films = "",
            isFavourite = isFavourite
        )

    fun mapEntityToDbModel(CharacterInfo: CharacterInfo) = CharacterInfoDb(
        name = CharacterInfo.name,
        gender = CharacterInfo.gender,
        mass = CharacterInfo.mass,
        height = CharacterInfo.height,
        homeWorld = CharacterInfo.homeWorld,
        films = CharacterInfo.films,
        isFavourite = CharacterInfo.isFavourite
    )
}