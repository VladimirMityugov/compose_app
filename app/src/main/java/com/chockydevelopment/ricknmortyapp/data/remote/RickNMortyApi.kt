package com.chockydevelopment.ricknmortyapp.data.remote

import com.chockydevelopment.ricknmortyapp.data.remote.characters_dto.CharactersDto
import com.chockydevelopment.ricknmortyapp.data.remote.episode_dto.EpisodesDto
import com.chockydevelopment.ricknmortyapp.data.remote.locations_dto.LocationsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickNMortyApi {

    @GET("character/?")
    suspend fun getAllCharacters(
        @Query("page")page:Int
    ): CharactersDto

    @GET("location/?")
    suspend fun getAllLocations(
        @Query("page")page:Int
    ): LocationsDto

    @GET("episode/")
    suspend fun getAllEpisodes(
        @Query("page")page:Int
    ): EpisodesDto

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}