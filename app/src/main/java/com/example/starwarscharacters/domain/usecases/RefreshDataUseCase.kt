package com.example.starwarscharacters.domain.usecases

import com.example.starwarscharacters.domain.repositories.CharactersRepository
import javax.inject.Inject

class RefreshDataUseCase @Inject constructor(
    private val repository: CharactersRepository,
) {
    suspend operator fun invoke() = repository.loadDataIfEmpty()
}