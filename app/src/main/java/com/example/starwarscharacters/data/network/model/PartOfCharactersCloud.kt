package com.example.starwarscharacters.data.network.model

import com.google.gson.annotations.SerializedName

data class PartOfCharactersCloud(
    @SerializedName("count") val count: String,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<CharacterCloud>,
)