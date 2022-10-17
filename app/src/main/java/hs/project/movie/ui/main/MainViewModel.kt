package hs.project.movie.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import hs.project.movie.data.model.PopularMovieItem
import hs.project.movie.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    val popularMovies: Flow<PagingData<PopularMovieItem>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { repository.popularMoviesPagingSource() }
    )
        .flow
        .cachedIn(viewModelScope)

}