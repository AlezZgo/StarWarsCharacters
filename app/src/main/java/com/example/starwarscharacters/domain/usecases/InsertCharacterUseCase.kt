package com.example.starwarscharacters.domain.usecases

import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.repositories.CharactersRepository

class InsertCharacterUseCase(
    private val repository: CharactersRepository
) {
    suspend operator fun invoke(character: CharacterInfo) = repository.insert(character)
}