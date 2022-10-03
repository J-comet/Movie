package hs.project.movie.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hs.project.movie.Config
import hs.project.movie.api.MovieAPI
import hs.project.movie.api.RetrofitClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Config.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): MovieAPI =
        RetrofitClient.getInstance().create(MovieAPI::class.java)

}