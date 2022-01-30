package com.example.starwarscharacters.presentation.characters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.starwarscharacters.data.repository.CharactersRepositoryImpl
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.usecases.GetCharacterListUseCase
import com.example.starwarscharacters.domain.usecases.InsertCharacterUseCase
import com.example.starwarscharacters.domain.usecases.LoadDataUseCase

class CharactersViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharactersRepositoryImpl(application)

    private val getCharactersListUseCase = GetCharacterListUseCase(repository)

    private val loadDataUseCase = LoadDataUseCase(repository)

    val insertCharacterUseCase = InsertCharacterUseCase(repository)

    var characterList : LiveData<List<CharacterInfo>>
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