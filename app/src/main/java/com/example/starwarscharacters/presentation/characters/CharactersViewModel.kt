package com.example.starwarscharacters.presentation.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.starwarscharacters.data.repository.CharactersRepositoryImpl
import com.example.starwarscharacters.domain.usecases.GetCharacterListUseCase
import com.example.starwarscharacters.domain.usecases.InsertCharacterUseCase
import com.example.starwarscharacters.domain.usecases.LoadDataUseCase

class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharactersRepositoryImpl(application)

    private val getCharactersListUseCase = GetCharacterListUseCase(repository)

    private val loadDataUseCase = LoadDataUseCase(repository)

    val insertCharacterUseCase = InsertCharacterUseCase(repository)

    val characterList = getCharactersListUseCase()

    init {
        //loadDataUseCase()
    }

}