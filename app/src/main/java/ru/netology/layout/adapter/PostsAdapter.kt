package ru.netology.layout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.netology.layout.databinding.ActivityCardPostFragmentBinding
import ru.netology.layout.dto.Post

//
interface OnInteractionListener  {
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
    fun onViewPost(post: Post) {}
    fun onRemove(post: Post) {}
    fun onEdit(post: Post) {}
    fun onVideo(post: Post){}
    fun onPost(post: Post){}
    fun onImage(image: String) {}
}







class PostsAdapter(
    private val onInteractionListener: OnInteractionListener,
) : PagingDataAdapter<Post, PostViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ActivityCardPostFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding,onInteractionListener)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position) ?: return
        holder.bind(post)

    }


    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem

    }

}





