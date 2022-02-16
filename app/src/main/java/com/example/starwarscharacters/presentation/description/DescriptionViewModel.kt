package com.example.starwarscharacters.presentation.description

import androidx.lifecycle.ViewModel
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.usecases.GetCharacterUseCase
import com.example.starwarscharacters.domain.usecases.InsertCharacterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(
    private val insertCharacterUseCase: InsertCharacterUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
) : ViewModel() {

    fun character(currentCharacter: CharacterInfo) =
        getCharacterUseCase.characterLiveData(currentCharacter.name)

    fun changeIsFavouriteStatus(character: CharacterInfo) {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            insertCharacterUseCase(character.copy(isFavourite = !character.isFavourite))
        }
    }

}