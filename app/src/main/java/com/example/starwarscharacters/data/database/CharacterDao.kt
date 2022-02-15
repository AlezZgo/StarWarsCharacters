package com.example.starwarscharacters.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(charactersList: List<CharacterInfoDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterInfoDb)

    @Query("SELECT * FROM characters WHERE name LIKE :filter ORDER BY name ASC")
    fun getCharactersList(filter: String): LiveData<List<CharacterInfoDb>>

    @Query("SELECT * FROM characters WHERE name == :name LIMIT 1")
    fun getCharacter(name: String): LiveData<CharacterInfoDb>

    @Query("SELECT * FROM characters WHERE isFavourite = 1 ORDER BY name ASC ")
    fun getFavouritesCharacters(): LiveData<List<CharacterInfoDb>>


}