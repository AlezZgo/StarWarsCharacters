package com.example.starwarscharacters.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.usecases.GetCharacterListUseCase
import com.example.starwarscharacters.domain.usecases.InsertCharacterUseCase
import com.example.starwarscharacters.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel @Inject constructor(
    private val getCharactersListUseCase: GetCharacterListUseCase,
    private val insertCharacterUseCase: InsertCharacterUseCase,
    loadDataUseCase: LoadDataUseCase,
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
        val filter = if (newFilter.isEmpty()) "%" else "%$newFilter%"
        this.filter.postValue(filter)
    }

    fun changeIsFavouriteStatus(character: CharacterInfo) {
        CoroutineScope(Dispatchers.IO + Job()).launch {
            insertCharacterUseCase(character.copy(isFavourite = !character.isFavourite))
        }
    }

}