package com.example.starwarscharacters.domain.usecases

import androidx.lifecycle.LiveData
import com.example.starwarscharacters.domain.entities.CharacterInfo
import com.example.starwarscharacters.domain.repositories.CharactersRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharactersRepository,
) {
    fun character(name: String): CharacterInfo = repository.character(name)

    fun characterLiveData(name: String): LiveData<CharacterInfo> =
        repository.characterLiveData(name)
}