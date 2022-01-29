package com.example.starwarscharacters.presentation.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwarscharacters.data.repository.CharactersRepositoryImpl
import com.example.starwarscharacters.domain.usecases.GetCharacterListUseCase
import com.example.starwarscharacters.domain.usecases.GetFavouriteCharactersUseCase
import com.example.starwarscharacters.domain.usecases.InsertCharacterUseCase
import com.example.starwarscharacters.domain.usecases.LoadDataUseCase

class FavouritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CharactersRepositoryImpl(application)

    private val getFavouriteCharactersListUseCase = GetFavouriteCharactersUseCase(repository)

    val insertCharacterUseCase = InsertCharacterUseCase(repository)

    val favouriteCharacterList = getFavouriteCharactersListUseCase()
}