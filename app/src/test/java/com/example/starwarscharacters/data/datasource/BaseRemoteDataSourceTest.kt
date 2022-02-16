package com.example.starwarscharacters.data.datasource

import com.example.starwarscharacters.data.network.ApiService
import com.example.starwarscharacters.data.network.model.CharacterCloud
import com.example.starwarscharacters.data.network.model.FilmCloud
import com.example.starwarscharacters.data.network.model.HomeWorldCloud
import com.example.starwarscharacters.data.network.model.PartOfCharactersCloud
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseRemoteDataSourceTest {

    @Test
    fun getAllCharacters_test_no_next() = runBlocking {
        val testService = TestServiceWithNoNext()
        val remoteDataSource = BaseRemoteDataSource(testService)
        val actual = remoteDataSource.getAllCharacters()
        assertEquals(1, actual.size)
    }

    @Test
    fun getAllCharacters_test_has_next() = runBlocking {
        val testService = TestServiceWithNext()
        val remoteDataSource = BaseRemoteDataSource(testService)
        val actual = remoteDataSource.getAllCharacters()
        assertEquals(2, actual.size)
    }


    private inner class TestServiceWithNoNext : ApiService {
        override suspend fun getPartOfCharacters() = PartOfCharactersCloud(
            "1", null, null, listOf(CharacterCloud(
                "name",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                "",
                "",
                ""
            ))
        )

        override suspend fun getPartOfCharactersByUrl(url: String) : PartOfCharactersCloud{
            throw java.lang.IllegalStateException("not used here")
        }

        override suspend fun getCharacterFilm(url: String): FilmCloud {
            throw IllegalStateException("not used here")
        }

        override suspend fun getCharacterHomeWorld(url: String): HomeWorldCloud {
            throw IllegalStateException("not used here")
        }


    }

    private inner class TestServiceWithNext : ApiService {
        override suspend fun getPartOfCharacters() = PartOfCharactersCloud(
            "1", "next", null, listOf(CharacterCloud(
                "name",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                "",
                "",
                ""
            ))
        )

        override suspend fun getPartOfCharactersByUrl(url: String) = PartOfCharactersCloud(
            "1", null, "prev", listOf(CharacterCloud(
                "name",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                emptyList(),
                emptyList(),
                emptyList(),
                emptyList(),
                "",
                "",
                ""
            ))
        )


        override suspend fun getCharacterFilm(url: String): FilmCloud {
            throw IllegalStateException("not used here")
        }

        override suspend fun getCharacterHomeWorld(url: String): HomeWorldCloud {
            throw IllegalStateException("not used here")
        }


    }



}