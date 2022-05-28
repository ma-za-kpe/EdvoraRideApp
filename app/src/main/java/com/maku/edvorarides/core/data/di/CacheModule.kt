package com.maku.edvorarides.core.data.di

import android.content.Context
import androidx.room.Room
import com.maku.edvorarides.core.data.local.cache.Cache
import com.maku.edvorarides.core.data.local.RidesDatabase
import com.maku.edvorarides.core.data.local.cache.RoomCacheImpl
import com.maku.edvorarides.core.data.local.dao.RidesDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

  @Binds
  abstract fun bindCache(cache: RoomCacheImpl): Cache

  companion object {

      @Provides
      @Singleton
      fun provideDatabase(
          @ApplicationContext context: Context
      ): RidesDatabase {
          return Room.databaseBuilder(
              context,
              RidesDatabase::class.java,
              "rides.db"
          )
              .build()
      }

      @Provides
      fun providePopularDrinksDao(
          ridesDatabase: RidesDatabase
      ): RidesDao = ridesDatabase.ridesDao()

  }
}