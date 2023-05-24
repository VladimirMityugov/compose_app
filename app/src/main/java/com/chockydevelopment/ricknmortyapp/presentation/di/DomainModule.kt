package com.chockydevelopment.ricknmortyapp.presentation.di

import com.chockydevelopment.ricknmortyapp.data.remote.RickNMortyApi
import com.chockydevelopment.ricknmortyapp.data.remote.repositories.RepositoryRemoteImpl
import com.chockydevelopment.ricknmortyapp.domain.repositories.repository_remote.RepositoryRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    @ViewModelScoped
    fun provideRepositoryRemote(rickNMortyApi: RickNMortyApi):RepositoryRemote{
        return RepositoryRemoteImpl(rickNMortyApi)
    }

}