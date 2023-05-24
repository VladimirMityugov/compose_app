package com.chockydevelopment.ricknmortyapp.data.remote.mappers_remote

import com.chockydevelopment.ricknmortyapp.data.remote.characters_dto.CharactersDto
import com.chockydevelopment.ricknmortyapp.data.remote.characters_dto.Info
import com.chockydevelopment.ricknmortyapp.data.remote.characters_dto.Location
import com.chockydevelopment.ricknmortyapp.data.remote.characters_dto.Origin
import com.chockydevelopment.ricknmortyapp.data.remote.characters_dto.Result
import com.chockydevelopment.ricknmortyapp.domain.models_remote.characters_model.*

class CharactersMapper {

    fun fromCharactersModel( charactersM: CharactersM): CharactersDto{
        return CharactersDto(
            info = fromInfoM(charactersM.infoM),
            results = charactersM.resultsM.map { fromResultM(it) }
        )
    }

    fun toCharactersModel( charactersDto: CharactersDto): CharactersM {
        return CharactersM(
            infoM = toInfoM(charactersDto.info),
            resultsM = charactersDto.results.map { toResultM(it) }
        )
    }

    private fun toInfoM(info: Info):InfoM{
        return InfoM(
            count = info.count,
            next = info.next,
            pages = info.pages,
            prev = info.prev
        )
    }

    private fun toResultM(result: Result): ResultM{
        return ResultM(
            created = result.created,
            episode = result.episode,
            id = result.id,
            gender = result.gender,
            image = result.image,
            locationM = toLocationM(result.location),
            name = result.name,
            originM = toOriginM(result.origin),
            species = result.species,
            status = result.status,
            type = result.type,
            url = result.url
        )
    }

    private fun toLocationM(location: Location):LocationM{
        return LocationM(
            name = location.name,
            url =  location.url
        )
    }

    private fun toOriginM(origin: Origin): OriginM{
        return OriginM(
            name = origin.name,
            url = origin.url
        )
    }

    private fun fromInfoM(infoM: InfoM):Info{
        return Info(
            count = infoM.count,
            next = infoM.next,
            pages = infoM.pages,
            prev = infoM.prev
        )
    }

    private fun fromResultM(resultM: ResultM): Result{
        return Result(
            created = resultM.created,
            episode = resultM.episode,
            id = resultM.id,
            gender = resultM.gender,
            image = resultM.image,
            location = fromLocationM(resultM.locationM),
            name = resultM.name,
            origin = fromOriginM(resultM.originM),
            species = resultM.species,
            status = resultM.status,
            type = resultM.type,
            url = resultM.url
        )
    }

    private fun fromLocationM(locationM: LocationM):Location{
        return Location(
            name = locationM.name,
            url =  locationM.url
        )
    }

    private fun fromOriginM(originM: OriginM): Origin{
        return Origin(
            name = originM.name,
            url = originM.url
        )
    }
}