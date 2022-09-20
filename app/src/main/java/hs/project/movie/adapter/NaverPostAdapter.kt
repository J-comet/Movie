package hs.project.movie.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import hs.project.movie.R
import hs.project.movie.data.ResponseNaverSearchPostItem
import hs.project.movie.databinding.ItemNaverPostBinding


class NaverPostAdapter : RecyclerView.Adapter<NaverPostAdapter.PostHolder>() {

    private var list = listOf<ResponseNaverSearchPostItem>()

    private lateinit var eventListener: OnPostClickListener

    interface OnPostClickListener {
        fun onItemClick(data: ResponseNaverSearchPostItem)
    }

    fun setOnItemClickListener(listener: OnPostClickListener) {
        this.eventListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = ItemNaverPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostHolder(binding, eventListener)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun setList(items: List<ResponseNaverSearchPostItem>) {
        if (!items.isNullOrEmpty()) {
            list = items
            notifyDataSetChanged()
        }
    }

    inner class PostHolder(private val itemBinding: ItemNaverPostBinding, listener: OnPostClickListener) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                listener.onItemClick(list[adapterPosition])
            }
        }

        fun bind(data: ResponseNaverSearchPostItem) {
            itemBinding.tvTitle.text = Html.fromHtml(data.title, Html.FROM_HTML_MODE_LEGACY)
            itemBinding.tvDate.text = "개봉날짜 ${data.pubDate}년"
            itemBinding.tvRating.text = "평점 ${data.userRating}"
            itemBinding.tvDirector.text = data.director.replace("|","")

            itemBinding.ivThumb.load(data.image) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_arrow_drop_down_circle_24)
                transformations(RoundedCornersTransformation(10f))
//                transformations(CircleCropTransformation())
            }
        }
    }
    
}