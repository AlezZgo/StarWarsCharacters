package com.example.starwarscharacters.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class CharacterDto(
    @SerializedName("name") val name : String,
    @SerializedName("height") val height : Int,
    @SerializedName("mass") val mass : Int,
    @SerializedName("hair_color") val hair_color : String,
    @SerializedName("skin_color") val skin_color : String,
    @SerializedName("eye_color") val eye_color : String,
    @SerializedName("birth_year") val birth_year : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("homeworld") val homeworld : String,
    @SerializedName("films") val films : List<FilmDto>,
    @SerializedName("species") val species : List<String>,
    @SerializedName("vehicles") val vehicles : List<String>,
    @SerializedName("starships") val starships : List<String>,
    @SerializedName("created") val created : String,
    @SerializedName("edited") val edited : String,
    @SerializedName("url") val url : String
)