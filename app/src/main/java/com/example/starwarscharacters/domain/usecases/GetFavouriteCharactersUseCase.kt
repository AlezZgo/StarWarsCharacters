package com.example.starwarscharacters.domain.usecases

import com.example.starwarscharacters.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetFavouriteCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository,
) {
    operator fun invoke() = repository.favouritesCharacters()
}