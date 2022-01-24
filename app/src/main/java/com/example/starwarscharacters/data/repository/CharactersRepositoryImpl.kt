package com.example.starwarscharacters.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.starwarscharacters.domain.Character
import com.example.starwarscharacters.domain.CharactersRepository

class CharactersRepositoryImpl(application: Application) : CharactersRepository {
    override fun getCharacter(name: String): LiveData<Character> {
        TODO("Not yet implemented")
    }

    override fun getCharacterList(): LiveData<List<Character>> {
        TODO("Not yet implemented")
    }
}