package com.example.starwarscharacters.presentation.description

import android.util.Log
import androidx.lifecycle.LiveData
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

    lateinit var character: LiveData<CharacterInfo>

    fun initCharacter(name: String) {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            character = getCharacterUseCase(name)
        }
    }

    fun changeIsFavouriteStatus() {
        Log.i("info", character.toString())
        CoroutineScope(Dispatchers.IO + Job()).launch {
            insertCharacterUseCase(character.value!!.copy(isFavourite = !character.value!!.isFavourite))
        }
    }

}