package com.example.starwarscharacters.domain

class GetCharacterListUseCase(
    private val repository: CharactersRepository
) {
    operator fun invoke() = repository.getCharacterList()
}