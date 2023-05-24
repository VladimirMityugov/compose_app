package com.chockydevelopment.ricknmortyapp.presentation.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chockydevelopment.ricknmortyapp.domain.models_remote.characters_model.ResultM
import com.chockydevelopment.ricknmortyapp.domain.use_case_remote.UseCaseRemote


class CharacterPagingSource(
    private val useCaseRemote: UseCaseRemote
) : PagingSource<Int, ResultM>() {

    override fun getRefreshKey(state: PagingState<Int, ResultM>): Int = FIRST_PAGE


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultM> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            useCaseRemote.getAllCharacters(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.resultsM,
                    prevKey = null,
                    nextKey = if (it.resultsM.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                LoadResult.Error(throwable = Throwable(it.message))
            }
        )
    }


    companion object {
        private const val FIRST_PAGE = 1
    }
}