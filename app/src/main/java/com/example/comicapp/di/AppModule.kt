package com.example.comicapp.di


import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.room.Room
import com.example.comicapp.common.Constants
import com.example.comicapp.data.local.FavoriteComicDatabase
import com.example.comicapp.data.remote.ComicApi
import com.example.comicapp.data.repository.ComicRepositoryImp
import com.example.comicapp.domain.respository.ComicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



/**
 * we tell dagger hill
 * this is how you can create dependencies
 *
 * we want to avoid hardcoding dependencies into our objects
 *
 * we inject the dependency from the outside, then we can easily swap dependency
 * if we want to use the fake repo, or the implementation
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideComicApi() : ComicApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ComicApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoriteComicsDatabase(app: Application): FavoriteComicDatabase {
        return Room.databaseBuilder(
            app,
            FavoriteComicDatabase::class.java,
            "favoriteComics.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideComicRepository(api : ComicApi, db: FavoriteComicDatabase): ComicRepository{
        return ComicRepositoryImp(api, db)
    }

    @Provides
    @Singleton
    fun provideTextToSpeech(application: Application): TextToSpeech {
        return TextToSpeech(application) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // TextToSpeech initialization successful
            } else {
                // TextToSpeech initialization failed
            }
        }
    }

}