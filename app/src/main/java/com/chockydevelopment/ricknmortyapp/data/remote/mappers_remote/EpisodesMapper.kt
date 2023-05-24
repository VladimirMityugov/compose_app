package com.chockydevelopment.ricknmortyapp.data.remote.mappers_remote

import com.chockydevelopment.ricknmortyapp.data.remote.episode_dto.EpisodesDto
import com.chockydevelopment.ricknmortyapp.data.remote.episode_dto.Info
import com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model.EpisodesM
import com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model.InfoM
import com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model.ResultM


class EpisodesMapper {

    fun toEpisodesM(episodesDto: EpisodesDto): EpisodesM {
        return EpisodesM(
            infoM = toInfoM(episodesDto.info),
            resultMS = episodesDto.results.map { toResultMS(it) })
    }

    private fun toInfoM(info: Info): InfoM {
        return InfoM(
            count = info.count,
            next = info.next,
            pages = info.pages,
            prev = info.prev
        )
    }

    private fun toResultMS(result: com.chockydevelopment.ricknmortyapp.data.remote.episode_dto.Result): ResultM {
        return ResultM(
            air_date = result.air_date,
            characters = result.characters,
            created = result.created,
            episode = result.episode,
            id = result.id,
            name = result.name,
            url = result.url
        )
    }

}