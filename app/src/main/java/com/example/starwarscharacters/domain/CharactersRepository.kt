package com.example.starwarscharacters.domain

import android.app.Application
import androidx.lifecycle.LiveData

interface CharactersRepository {
    fun getCharacter(name:String): LiveData<Character>

    fun getCharacterList(): LiveData<List<Character>>
}