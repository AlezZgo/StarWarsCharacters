package com.example.starwarscharacters.domain.usecases

import com.example.starwarscharacters.domain.repositories.CharactersRepository

class GetCharacterListUseCase(
    private val repository: CharactersRepository,
) {
    operator fun invoke(filter: String) = repository.getCharacterList(filter)
}