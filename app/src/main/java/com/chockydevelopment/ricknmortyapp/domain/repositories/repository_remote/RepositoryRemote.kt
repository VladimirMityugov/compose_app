package com.chockydevelopment.ricknmortyapp.domain.repositories.repository_remote

import com.chockydevelopment.ricknmortyapp.domain.models_remote.characters_model.CharactersM
import com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model.EpisodesM
import com.chockydevelopment.ricknmortyapp.domain.models_remote.loctions_model.LocationsM

interface RepositoryRemote {

    suspend fun getAllCharacters(page:Int): CharactersM

    suspend fun getAllLocations(page: Int): LocationsM

    suspend fun getAllEpisodes(page: Int): EpisodesM
}