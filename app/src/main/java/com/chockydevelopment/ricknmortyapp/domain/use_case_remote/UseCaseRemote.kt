package com.chockydevelopment.ricknmortyapp.domain.use_case_remote

import com.chockydevelopment.ricknmortyapp.domain.models_remote.characters_model.CharactersM
import com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model.EpisodesM
import com.chockydevelopment.ricknmortyapp.domain.models_remote.loctions_model.LocationsM
import com.chockydevelopment.ricknmortyapp.domain.repositories.repository_remote.RepositoryRemote
import javax.inject.Inject

class UseCaseRemote @Inject constructor(private val repositoryRemote: RepositoryRemote) {

    suspend fun getAllCharacters(page: Int): CharactersM {
        return repositoryRemote.getAllCharacters(page)
    }

    suspend fun getAllLocations(page:Int): LocationsM {
        return repositoryRemote.getAllLocations(page)
    }

    suspend fun getAllEpisodes(page:Int): EpisodesM{
        return repositoryRemote.getAllEpisodes(page)
    }
}