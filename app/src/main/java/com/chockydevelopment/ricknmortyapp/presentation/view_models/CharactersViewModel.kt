package com.chockydevelopment.ricknmortyapp.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.chockydevelopment.ricknmortyapp.domain.models_remote.characters_model.ResultM
import com.chockydevelopment.ricknmortyapp.domain.use_case_remote.UseCaseRemote
import com.chockydevelopment.ricknmortyapp.presentation.paging_sources.CharacterPagingSource
import com.chockydevelopment.ricknmortyapp.presentation.paging_sources.EpisodesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val useCaseRemote: UseCaseRemote
) : ViewModel() {

    private val _characterId = MutableStateFlow<Int?>(null)
    val characterId = _characterId.asStateFlow()

    fun getAllCharacters(id: Int?): Flow<PagingData<ResultM>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                CharacterPagingSource(
                    useCaseRemote = useCaseRemote
                )
            }
        ).flow
            .map {
                if (id != null) {
                    it.filter { result -> result.id == id }
                } else it
            }
            .cachedIn(viewModelScope)
    }

    fun getAllEpisodes(ids: List<Int>): Flow<PagingData<com.chockydevelopment.ricknmortyapp.domain.models_remote.episode_model.ResultM>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                EpisodesPagingSource(
                    useCaseRemote = useCaseRemote
                )
            }
        ).flow
            .map {
                if (ids.isNotEmpty()) {
                    it.filter { result -> ids.contains(result.id) }
                } else it
            }
            .cachedIn(viewModelScope)
    }

    fun setCharacterId(id: Int) {
        viewModelScope.launch {
            _characterId.value = id
        }
    }

}