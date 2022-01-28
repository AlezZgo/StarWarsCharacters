package com.example.starwarscharacters.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(charactersList: List<CharacterInfoDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterInfoDbModel)

    @Query("SELECT * FROM characters ORDER BY name ASC")
    fun getCharactersList(): LiveData<List<CharacterInfoDbModel>>

    @Query("SELECT * FROM characters WHERE name == :name LIMIT 1")
    fun getCharacter(name: String): CharacterInfoDbModel

    @Query("SELECT * FROM characters WHERE isFavourite = 1 ORDER BY name ASC ")
    fun getFavouritesCharacters(): LiveData<List<CharacterInfoDbModel>>



}