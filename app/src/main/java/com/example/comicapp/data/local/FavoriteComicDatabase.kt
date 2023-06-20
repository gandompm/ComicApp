package com.example.comicapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.comicapp.domain.model.Comic


@Database(
    entities = [Comic::class],
    version = 2
)
abstract class FavoriteComicDatabase : RoomDatabase() {

    /**
     * Provides access to the data access object (DAO) for favorite comics
     */
    abstract val dao: FavoriteComicDao
}