package com.devdroid07.authui_kotlincompose.auth.di

import com.devdroid07.authui_kotlincompose.auth.domain.validator.UserDataValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideUserDataValidator(): UserDataValidator{
        return UserDataValidator()
    }
}