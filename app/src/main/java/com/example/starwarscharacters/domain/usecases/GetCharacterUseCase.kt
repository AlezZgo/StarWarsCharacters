package com.example.starwarscharacters.domain.usecases

import com.example.starwarscharacters.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharactersRepository,
) {
    operator fun invoke(name: String) = repository.getCharacter(name)
}