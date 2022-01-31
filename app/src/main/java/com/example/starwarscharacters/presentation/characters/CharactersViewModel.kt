package com.example.starwarscharacters.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.usecases.GetCharacterListUseCase
import com.example.starwarscharacters.domain.usecases.InsertCharacterUseCase
import com.example.starwarscharacters.domain.usecases.LoadDataUseCase
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharacterListUseCase,
    private val loadDataUseCase: LoadDataUseCase,
    val insertCharacterUseCase: InsertCharacterUseCase,
) : ViewModel() {

    var characterList: LiveData<List<CharacterInfo>>
    var filter = MutableLiveData("%")

    init {
        loadDataUseCase()
        characterList = Transformations.switchMap(filter) { filter ->
            getCharactersListUseCase(filter)
        }
    }

    fun setFilter(newFilter: String) {
        val filter = when {
            newFilter.isEmpty() -> "%"
            else -> "%$newFilter%"
        }
        this.filter.postValue(filter)
    }

}