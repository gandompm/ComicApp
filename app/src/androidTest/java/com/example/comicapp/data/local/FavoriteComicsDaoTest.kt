package com.example.comicapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.comicapp.domain.model.Comic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
// we tell junit by this that these tests are instrumented tests
@RunWith(AndroidJUnit4::class)
// we tell junit by this command that these are unit tests
@SmallTest
class FavoriteComicsDaoTest {

    private lateinit var database: FavoriteComicDatabase
    private lateinit var dao: FavoriteComicDao

    /**
     * we need to explicitly tell junit
     * that we want to execute all the functions
     * one after the other
     * in the same thread
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * we want to make sure that we have entirely new database
     * for each test case
     *
     * because it should be independent
     * so the initialization should happen inside @before
     * it will be executed before each test case
     */
    @Before
    fun setup(){
        /**
         * the difference between DatabaseBuilder and
         * inMemoryDatabaseBuilder is that the second one
         * it will only hold data in the ram not in the
         * persistence database
         * because we need to store it only for that test
          */
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoriteComicDatabase::class.java
        )
            /**
             * f we apply this function, we allow it to create
             * it in the main thread
             * because in test cases we want that to be executed
             * in the main thread to prevent manipulating data
             * that could happen by multithreading
             */
            .allowMainThreadQueries()
            .build()

        dao = database.dao
    }

    // it will be executed after each test
    @After
    fun teardown(){
        database.close()
    }

    /**
     * since we don't want multithreading,
     * we will use runBlocking for the coroutine here
     * that is a way to use coroutine in the main thread
     */
    @Test
    fun insertComic() = runBlockingTest {
        val comicItem = Comic("alt","1", "url", "2", 23, "title", "", "3")

        dao.insertComic(comicItem)

        val doesExists = dao.doesComicExist(23)

        assert(doesExists)
    }

    @Test
    fun deleteComic() = runBlockingTest {
        val comicItem = Comic("alt","1", "url", "2", 23, "title", "", "3")
        dao.insertComic(comicItem)
        dao.deleteItemById(23)

        val doesExists = dao.doesComicExist(23)

        assert(!doesExists)
    }

    @Test
    fun searchingFunctionalityInDataBase() = runBlockingTest {


        dao.insertComic(Comic("alt","1", "url", "2", 23, "title", "", "3"))
        dao.insertComic(Comic("alt","1", "url", "2", 25, "title", "", "3"))
        dao.insertComic(Comic("alt","1", "url", "2", 232, "title", "", "3"))
        dao.insertComic(Comic("alt","1", "url", "2", 203, "title", "", "3"))

        val doesExist = dao.doesComicExist(232)
        assert(doesExist)
    }

    @Test
    fun searchingFunctionality2InDataBase() = runBlockingTest {
        dao.insertComic(Comic("alt","1", "url", "2", 23, "title", "", "3"))
        dao.insertComic(Comic("alt","1", "url", "2", 25, "title", "", "3"))
        dao.insertComic(Comic("alt","1", "url", "2", 232, "title", "", "3"))
        dao.insertComic(Comic("alt","1", "url", "2", 203, "title", "", "3"))

        val doesExist = dao.doesComicExist(55)
        assert(!doesExist)
    }


}