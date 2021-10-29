package com.example.moviestestapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(
    private val api: ApiService
): PagingSource<Int, Result>()  {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
        val pageSize = params.loadSize

        return try {
            val response = api.getMovies("a86cd96f8b111284d749bd3d94ca9ca7",  pageNumber, pageSize )
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (pageNumber == INITIAL_PAGE_NUMBER) null else pageNumber - 1,
                nextKey = if (movies.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }


    }


    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}