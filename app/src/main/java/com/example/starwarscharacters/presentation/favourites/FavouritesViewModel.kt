package com.example.starwarscharacters.presentation.favourites

import androidx.lifecycle.ViewModel
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.usecases.GetFavouriteCharactersUseCase
import com.example.starwarscharacters.domain.usecases.InsertCharacterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesViewModel @Inject constructor(
    getFavouriteCharactersListUseCase: GetFavouriteCharactersUseCase,
    private val insertCharacterUseCase: InsertCharacterUseCase,
) : ViewModel() {

    val favouriteCharacterList = getFavouriteCharactersListUseCase()

    fun changeIsFavouriteStatus(character: CharacterInfo) {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            insertCharacterUseCase(character.copy(isFavourite = !character.isFavourite))
        }
    }


}