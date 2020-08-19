package com.ramadan.hiltplayground.demo.di

import com.ramadan.hiltplayground.demo.repository.BlogRepository
import com.ramadan.hiltplayground.demo.retrofit.BlogAPI
import com.ramadan.hiltplayground.demo.retrofit.NetworkMapper
import com.ramadan.hiltplayground.demo.room.BlogDAO
import com.ramadan.hiltplayground.demo.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providesBlogRepository(blogDAO: BlogDAO,api: BlogAPI,networkMapper: NetworkMapper,cacheMapper: CacheMapper):BlogRepository{
        return  BlogRepository(blogDAO,api,networkMapper,cacheMapper)
    }
}