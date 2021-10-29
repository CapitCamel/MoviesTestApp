package com.example.moviestestapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.moviestestapp.data.ApiService
import com.example.moviestestapp.data.MoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularFilmsViewModel @Inject constructor(
    private val api: ApiService
): ViewModel() {
    val movies =
        Pager(
            PagingConfig(pageSize = 5, enablePlaceholders = false),
            pagingSourceFactory = { MoviesPagingSource(api) }
        ).liveData.cachedIn(viewModelScope)

}