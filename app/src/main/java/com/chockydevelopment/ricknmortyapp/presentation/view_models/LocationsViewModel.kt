package com.chockydevelopment.ricknmortyapp.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chockydevelopment.ricknmortyapp.domain.models_remote.loctions_model.ResultM
import com.chockydevelopment.ricknmortyapp.domain.use_case_remote.UseCaseRemote
import com.chockydevelopment.ricknmortyapp.presentation.paging_sources.LocationsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val useCaseRemote: UseCaseRemote
): ViewModel() {


    val locations: Flow<PagingData<ResultM>> = Pager(
            config = PagingConfig(pageSize = 20,
            initialLoadSize = 20),
            pagingSourceFactory = {
                LocationsPagingSource(
                    useCaseRemote = useCaseRemote
                )
            }
        ).flow
        .cachedIn(viewModelScope)

}