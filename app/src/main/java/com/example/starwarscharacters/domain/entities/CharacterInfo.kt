package com.example.starwarscharacters.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterInfo(
    val name: String,
    val gender: String,
    val mass: String,
    val height: String,
    val homeWorld: String,
    val films: String,
    var isFavourite: Boolean,
) : Parcelable
