package com.example.starwarscharacters.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterListDto(
    @SerializedName("Data")
    @Expose
    val names: List<CharacterDto>? = null
)