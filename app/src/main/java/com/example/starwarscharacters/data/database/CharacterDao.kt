package com.example.starwarscharacters.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters ORDER BY height DESC")
    fun getCharacterList(): LiveData<List<CharacterDbModel>>

    @Query("SELECT * FROM characters WHERE name == :name LIMIT 1")
    fun getCharacter(name: String): LiveData<CharacterDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(priceList: List<CharacterDbModel>)
}