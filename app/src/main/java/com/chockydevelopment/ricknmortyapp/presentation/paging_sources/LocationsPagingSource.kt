package com.chockydevelopment.ricknmortyapp.presentation.paging_sources


import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chockydevelopment.ricknmortyapp.domain.models_remote.loctions_model.ResultM
import com.chockydevelopment.ricknmortyapp.domain.use_case_remote.UseCaseRemote


class LocationsPagingSource(
    private val useCaseRemote: UseCaseRemote
) : PagingSource<Int, ResultM>() {

    override fun getRefreshKey(state: PagingState<Int, ResultM>): Int = FIRST_PAGE

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultM> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            useCaseRemote.getAllLocations(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.resultMS,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (it.resultMS.isEmpty()) null else page + 1
                )
            },
            onFailure = {
                LoadResult.Error(
                    throwable = Throwable(it.message)
                )
            }
        )
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}