package com.example.starwarscharacters.data.network.model

import com.google.gson.annotations.SerializedName

data class CharacterHalfListDto(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<CharacterDto>
)