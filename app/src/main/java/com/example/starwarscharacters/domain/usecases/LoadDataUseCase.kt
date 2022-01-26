package com.example.starwarscharacters.domain.usecases

import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.repositories.CharactersRepository

class LoadDataUseCase(
    private val repository: CharactersRepository
) {
    operator fun invoke() = repository.loadData()
}