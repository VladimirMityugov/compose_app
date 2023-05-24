package com.chockydevelopment.ricknmortyapp.data.remote.mappers_remote

import com.chockydevelopment.ricknmortyapp.data.remote.locations_dto.Info
import com.chockydevelopment.ricknmortyapp.data.remote.locations_dto.LocationsDto
import com.chockydevelopment.ricknmortyapp.domain.models_remote.loctions_model.InfoM
import com.chockydevelopment.ricknmortyapp.domain.models_remote.loctions_model.LocationsM
import com.chockydevelopment.ricknmortyapp.domain.models_remote.loctions_model.ResultM


class LocationsMapper {

    fun fromLocationsModel(locationsM: LocationsM): LocationsDto {
        return LocationsDto(
            info = fromInfoM(locationsM.infoM),
            results = locationsM.resultMS.map { fromResultM(it) }
        )
    }

    fun toLocationsModel(locationsDto: LocationsDto): LocationsM {
        return LocationsM(
            infoM = toInfoM(locationsDto.info),
            resultMS = locationsDto.results.map { toResultM(it) }
        )
    }

    private fun toInfoM(info: Info): InfoM {
        return InfoM(
            count = info.count,
            next = info.next,
            pages = info.pages,
            prev = info.prev
        )
    }

    private fun toResultM(result: com.chockydevelopment.ricknmortyapp.data.remote.locations_dto.Result): ResultM {
        return ResultM(
            created = result.created,
            dimension = result.dimension,
            id = result.id,
            name = result.name,
            type = result.type,
            url = result.url,
            residents = result.residents
        )
    }

    private fun fromInfoM(infoM: InfoM): Info {
        return Info(
            count = infoM.count,
            next = infoM.next,
            pages = infoM.pages,
            prev = infoM.prev
        )
    }

    private fun fromResultM(resultM: ResultM): com.chockydevelopment.ricknmortyapp.data.remote.locations_dto.Result {
        return com.chockydevelopment.ricknmortyapp.data.remote.locations_dto.Result(
            created = resultM.created,
            dimension = resultM.dimension,
            id = resultM.id,
            name = resultM.name,
            residents = resultM.residents,
            type = resultM.type,
            url = resultM.url
        )
    }
}