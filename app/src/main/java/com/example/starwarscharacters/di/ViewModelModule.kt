package com.example.starwarscharacters.di

import androidx.lifecycle.ViewModel
import com.example.starwarscharacters.presentation.characters.CharactersViewModel
import com.example.starwarscharacters.presentation.description.DescriptionViewModel
import com.example.starwarscharacters.presentation.favourites.FavouritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouritesViewModel::class)
    fun bindFavouritesViewModel(viewModel: FavouritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DescriptionViewModel::class)
    fun bindDescriptionViewModel(viewModel: DescriptionViewModel): ViewModel
}