package hs.project.movie.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hs.project.movie.data.model.PopularMovieItem
import hs.project.movie.databinding.ItemPopularMovieBinding
import hs.project.movie.ui.detail.DetailMovieActivity

class PopularMovieAdapter : ListAdapter<PopularMovieItem, PopularMovieAdapter.PopularHolder>(
    PopularMovieDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder {
        return PopularHolder(
            ItemPopularMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularHolder, position: Int) {
        holder.bind(currentList[holder.adapterPosition])
    }

    inner class PopularHolder(private val itemBinding: ItemPopularMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.setClickListener {
                Log.d("clickListener", currentList[adapterPosition].title)
                val intent = Intent(itemBinding.root.context, DetailMovieActivity::class.java)
                intent.putExtra("id", currentList[adapterPosition].id)
                ContextCompat.startActivity(itemBinding.root.context, intent, null)
            }
        }

        fun bind(data: PopularMovieItem) {
            itemBinding.data = data
            Log.e("data", "data = $data")
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