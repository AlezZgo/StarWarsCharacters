package com.example.starwarscharacters.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class CharacterDto(
    @SerializedName("NAME")
    @Expose
    val name: String?,
    @SerializedName("HEIGHT")
    @Expose
    val height: String?,
    @PrimaryKey
    @SerializedName("MASS")
    @Expose
    val mass: String,
    @SerializedName("HAIRCOLOR")
    @Expose
    val hairColor: String?,
    @SerializedName("SKINCOLOR")
    @Expose
    val skinColor: String?,
    @SerializedName("EYECOLOR")
    @Expose
    val eyeColor: String?,
    @SerializedName("BIRTHYEAR")
    @Expose
    val birthYear: String?,
    @SerializedName("GENDER")
    @Expose
    val gender: String?,
    @SerializedName("HOMEWORLD")
    @Expose
    val homeWorld: String?,
    @SerializedName("FILMS")
    @Expose
    val films: String?,
    @SerializedName("SPECIES")
    @Expose
    val species: String?,
    @SerializedName("VEHICLES")
    @Expose
    val vehicles: String?,
    @SerializedName("STARSHIPS")
    @Expose
    val starShips: String?,
    @SerializedName("CREATED")
    @Expose
    val created: String?,
    @SerializedName("EDITED")
    @Expose
    val edited: String?,
    @SerializedName("URL")
    @Expose
    val url: String?,
)