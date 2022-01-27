package com.example.starwarscharacters.domain.usecases

import com.example.starwarscharacters.domain.repositories.CharactersRepository

class GetFavouriteCharactersUseCase(
    private val repository: CharactersRepository,
) {
    operator fun invoke() = repository.getFavouritesCharacters()
}