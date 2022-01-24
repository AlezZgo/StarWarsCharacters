package com.example.starwarscharacters.domain

class GetCharacterUseCase(
    private val repository: CharactersRepository
) {
    operator fun invoke(name: String) = repository.getCharacter(name)
}