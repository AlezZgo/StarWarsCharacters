package com.example.starwarscharacters.domain

class GetCharacterListUseCase(
    private val repository: CharactersRepository,
    private val filter: String
) {
    operator fun invoke() = repository.getCharacterList(filter)
}