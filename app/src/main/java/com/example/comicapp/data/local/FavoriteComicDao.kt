package com.example.comicapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.comicapp.domain.model.Comic

/**
 * an interface that defines the data access operations in database
 */
@Dao
interface FavoriteComicDao {

    /**
     * Inserts a comic into the database. If a comic with the same 'num' already exists,
     * it will be replaced with the new comic.
     *
     * @param comic The comic to be inserted.
     * @return The row ID of the inserted comic.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComic(
        comic: Comic
    ): Long

    /**
     * Deletes a comic from the database based on its 'num' value.
     *
     * @param itemNum The 'num' value of the comic to be deleted.
     * @return The number of rows affected by the deletion operation.
     */
    @Query("DELETE FROM comic WHERE num = :itemNum")
    suspend fun deleteItemById(itemNum: Int): Int

    /**
     * Retrieves all favorite comics from the database.
     *
     * @return A list of all favorite comics in the database.
     */
    @Query("SELECT * FROM comic")
    suspend fun showAllFavoriteComics(): List<Comic>

    /**
     * Checks if a comic with the given 'num' value exists in the database.
     *
     * @param itemNum The 'num' value of the comic to check.
     * @return True if the comic exists, false otherwise.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM comic WHERE num = :itemNum)")
    suspend fun doesComicExist(itemNum: Int): Boolean

}