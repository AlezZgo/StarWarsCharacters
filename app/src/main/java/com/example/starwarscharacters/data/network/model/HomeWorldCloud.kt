package com.example.starwarscharacters.data.network.model

import com.google.gson.annotations.SerializedName

data class HomeWorldCloud(

    @SerializedName("name") val name: String,
    @SerializedName("rotation_period") val rotation_period: String,
    @SerializedName("orbital_period") val orbital_period: String,
    @SerializedName("diameter") val diameter: String,
    @SerializedName("climate") val climate: String,
    @SerializedName("gravity") val gravity: String,
    @SerializedName("terrain") val terrain: String,
    @SerializedName("surface_water") val surface_water: String,
    @SerializedName("population") val population: String,
    @SerializedName("residents") val residents: List<String>,
    @SerializedName("films") val films: List<String>,
    @SerializedName("created") val created: String,
    @SerializedName("edited") val edited: String,
    @SerializedName("url") val url: String,
)