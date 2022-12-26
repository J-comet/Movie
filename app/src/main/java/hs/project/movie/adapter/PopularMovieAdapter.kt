package hs.project.movie.adapter

import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hs.project.movie.data.model.PopularMovieItem
import hs.project.movie.databinding.ItemPopularMovieBinding
import hs.project.movie.utils.setOnClickListener
import timber.log.Timber

class PopularMovieAdapter : PagingDataAdapter<PopularMovieItem, PopularMovieAdapter.PopularHolder>(
    PopularMovieDiffUtilCallback()
) {

    private var eventListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.eventListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(pair: Pair<View, String>, data: PopularMovieItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder {
        return PopularHolder(
            ItemPopularMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            eventListener
        )
    }

    override fun onBindViewHolder(holder: PopularHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class PopularHolder(
        private val itemBinding: ItemPopularMovieBinding,
        private val eventListener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: PopularMovieItem?) {
            if (data == null) {
                return
            }

            itemBinding.data = data

            itemBinding.root.setOnClickListener(600) {
                Timber.d(data.title)
                val pos = bindingAdapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    eventListener?.onItemClick(Pair(itemBinding.ivThumb,itemBinding.ivThumb.transitionName), data)
                }
            }
        }
    }

    private class PopularMovieDiffUtilCallback : DiffUtil.ItemCallback<PopularMovieItem>() {

        override fun areItemsTheSame(
            oldItem: PopularMovieItem,
            newItem: PopularMovieItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PopularMovieItem,
            newItem: PopularMovieItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}