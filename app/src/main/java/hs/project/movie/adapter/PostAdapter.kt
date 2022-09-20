package hs.project.movie.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import hs.project.movie.R
import hs.project.movie.data.DailyBoxOffice
import hs.project.movie.databinding.ItemPostBinding
import hs.project.movie.ui.SearchActivity


class PostAdapter : ListAdapter<DailyBoxOffice, PostAdapter.PostHolder>(
    PostDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostHolder(private val itemBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.setClickListener {
                Log.d("clickListener", getItem(adapterPosition).movieNm)
                val intent = Intent(itemBinding.root.context, SearchActivity::class.java)
                ContextCompat.startActivity(itemBinding.root.context, intent, null)
            }
        }

        fun bind(data: DailyBoxOffice) {
            itemBinding.data = data
        }
    }

    private class PostDiffUtilCallback : DiffUtil.ItemCallback<DailyBoxOffice>() {

        override fun areItemsTheSame(
            oldItem: DailyBoxOffice,
            newItem: DailyBoxOffice
        ): Boolean {
            return oldItem.movieCd == newItem.movieCd
        }

        override fun areContentsTheSame(
            oldItem: DailyBoxOffice,
            newItem: DailyBoxOffice
        ): Boolean {
            return oldItem.movieCd == newItem.movieCd
        }
    }
}