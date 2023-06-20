package com.example.comicapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comicapp.domain.model.Comic


@Dao
interface FavoriteComicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(
        comic: Comic
    ): Long

    @Query("DELETE FROM comic WHERE num = :itemNum")
    suspend fun deleteItemById(itemNum: Int): Int

    @Query("SELECT * FROM comic")
    suspend fun showAllFavoriteComics(): List<Comic>

    @Query("SELECT EXISTS(SELECT 1 FROM comic WHERE num = :itemNum)")
    suspend fun doesComicExist(itemNum: Int): Boolean

}