package hs.project.movie.data.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import hs.project.movie.data.RemoteDataSource
import hs.project.movie.data.model.PopularMovieItem
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PopularMoviesPagingSource(
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, PopularMovieItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = remoteDataSource.getPopularMovies(position).body()
            val results = response!!.results
            LoadResult.Page(
                data = results,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (results.isEmpty()) null else position + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularMovieItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}