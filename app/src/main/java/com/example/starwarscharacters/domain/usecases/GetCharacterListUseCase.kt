package com.example.starwarscharacters.domain.usecases

import com.example.starwarscharacters.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val repository: CharactersRepository,
) {
    operator fun invoke(filter: String) = repository.getCharacterList(filter)
}