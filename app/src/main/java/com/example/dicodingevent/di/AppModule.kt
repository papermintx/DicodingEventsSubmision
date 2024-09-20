package com.example.dicodingevent.di

import com.example.dicodingevent.data.ApiService
import com.example.dicodingevent.data.repository.RemoteDataRepositoryImpl
import com.example.dicodingevent.domain.repository.RemoteDataRepository
import com.example.dicodingevent.domain.usecase.GetEventUsecase
import com.example.dicodingevent.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshit(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideApiService(moshi: Moshi): ApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(apiService: ApiService): RemoteDataRepository{
        return RemoteDataRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: RemoteDataRepository): GetEventUsecase{
        return GetEventUsecase(repository)
    }
}