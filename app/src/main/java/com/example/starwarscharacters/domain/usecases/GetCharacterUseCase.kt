package com.example.starwarscharacters.domain.usecases

import com.example.starwarscharacters.domain.repositories.CharactersRepository

class GetCharacterUseCase(
    private val repository: CharactersRepository,
) {
    operator fun invoke(name: String) = repository.getCharacter(name)
}