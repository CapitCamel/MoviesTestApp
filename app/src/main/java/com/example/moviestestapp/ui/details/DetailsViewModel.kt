package com.example.moviestestapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviestestapp.data.ApiService
import com.example.moviestestapp.data.DetailsMovie
import com.example.moviestestapp.data.PopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val api: ApiService
): ViewModel() {
    private val _movie = MutableLiveData<DetailsMovie>()
    val movie: LiveData<DetailsMovie>
        get() = _movie

    fun getDetailsMovie(id: Int) = viewModelScope.launch{
        _movie.value = api.getDetails(id, "a86cd96f8b111284d749bd3d94ca9ca7")
    }

}