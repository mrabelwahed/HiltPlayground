package com.ramadan.hiltplayground.demo.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ramadan.hiltplayground.demo.room.BlogDAO
import com.ramadan.hiltplayground.demo.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context):BlogDatabase{
        return Room.databaseBuilder(
             context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesBlogDAO(blogDatabase: BlogDatabase):BlogDAO{
        return blogDatabase.blogDAO()
    }
}