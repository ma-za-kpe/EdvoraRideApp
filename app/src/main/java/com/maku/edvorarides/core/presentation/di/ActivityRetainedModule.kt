package com.maku.edvorarides.core.presentation.di

import com.maku.edvorarides.core.data.repositories.RideRepository
import com.maku.edvorarides.core.data.repositories.RideRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import io.reactivex.disposables.CompositeDisposable

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

  @Binds
  @ActivityRetainedScoped
  abstract fun bindPopularDrinkRepository(repository: RideRepositoryImpl): RideRepository

  companion object {
    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()
  }
}