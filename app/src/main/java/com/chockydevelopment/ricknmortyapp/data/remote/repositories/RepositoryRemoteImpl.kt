package com.chockydevelopment.ricknmortyapp.data.remote.repositories

import com.chockydevelopment.ricknmortyapp.data.remote.RickNMortyApi
import com.chockydevelopment.ricknmortyapp.data.remote.mappers_remote.CharactersMapper
import com.chockydevelopment.ricknmortyapp.data.remote.mappers_remote.EpisodesMapper
import com.chockydevelopment.ricknmortyapp.data.remote.mappers_remote.LocationsMapper
import com.chockydevelopment.ricknmortyapp.domain.models_remote.characters_model.CharactersM
import com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model.EpisodesM
import com.chockydevelopment.ricknmortyapp.domain.models_remote.loctions_model.LocationsM
import com.chockydevelopment.ricknmortyapp.domain.repositories.repository_remote.RepositoryRemote
import javax.inject.Inject

class RepositoryRemoteImpl @Inject constructor(private val rickNMortyApi: RickNMortyApi) :
    RepositoryRemote {

    override suspend fun getAllCharacters(page: Int): CharactersM {
        val mapper = CharactersMapper()
        val result = rickNMortyApi.getAllCharacters(page)
        return mapper.toCharactersModel(result)
    }

    override suspend fun getAllLocations(page: Int): LocationsM {
        val mapper = LocationsMapper()
        val result = rickNMortyApi.getAllLocations(page)
        return mapper.toLocationsModel(result)
    }

    override suspend fun getAllEpisodes(page: Int): EpisodesM {
        val mapper = EpisodesMapper()
        val result = rickNMortyApi.getAllEpisodes(page)
        return mapper.toEpisodesM(result)
    }

}