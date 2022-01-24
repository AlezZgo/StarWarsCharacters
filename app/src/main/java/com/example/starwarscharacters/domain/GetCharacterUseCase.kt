package com.example.starwarscharacters.domain

class GetCharacterUseCase(
    private val repository: CharactersRepository,
    private val name: String
) {
    operator fun invoke() = repository.getCharacter(name)
}