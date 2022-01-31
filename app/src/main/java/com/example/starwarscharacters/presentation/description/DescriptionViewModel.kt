package com.example.starwarscharacters.presentation.description

import androidx.lifecycle.ViewModel
import com.example.starwarscharacters.domain.usecases.GetCharacterUseCase
import com.example.starwarscharacters.domain.usecases.InsertCharacterUseCase
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(
    val insertCharacterUseCase: InsertCharacterUseCase,
    val getCharacterUseCase: GetCharacterUseCase,
) : ViewModel()