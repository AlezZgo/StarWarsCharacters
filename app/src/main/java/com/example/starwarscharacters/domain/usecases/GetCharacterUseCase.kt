package com.example.starwarscharacters.domain.usecases

import androidx.lifecycle.LiveData
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharactersRepository,
) {
    operator fun invoke(name: String): LiveData<CharacterInfo> = repository.character(name)
}