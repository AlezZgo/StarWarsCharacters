package com.example.starwarscharacters.presentation.description

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.starwarscharacters.data.repository.CharactersRepositoryImpl
import com.example.starwarscharacters.domain.usecases.GetCharacterUseCase
import com.example.starwarscharacters.domain.usecases.InsertCharacterUseCase

class DescriptionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharactersRepositoryImpl(application)

    val insertCharacterUseCase = InsertCharacterUseCase(repository)

    val getCharacterUseCase = GetCharacterUseCase(repository)
}