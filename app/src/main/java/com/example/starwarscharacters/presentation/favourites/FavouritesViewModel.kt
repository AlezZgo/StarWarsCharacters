package com.example.starwarscharacters.presentation.favourites

import androidx.lifecycle.ViewModel
import com.example.starwarscharacters.domain.usecases.GetFavouriteCharactersUseCase
import com.example.starwarscharacters.domain.usecases.InsertCharacterUseCase
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    private val getFavouriteCharactersListUseCase: GetFavouriteCharactersUseCase,
    val insertCharacterUseCase: InsertCharacterUseCase,
) : ViewModel() {
    val favouriteCharacterList = getFavouriteCharactersListUseCase()
}