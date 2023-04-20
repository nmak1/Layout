package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.netology.layout.adapter.PostViewHolder
import ru.netology.layout.databinding.CardPostBinding
import ru.netology.layout.dto.Post

//
//interface OnInteractionListener {
//    fun onLike(post: Post) {}
//    fun onShare(post: Post) {}
//    fun onViewPost(post: Post) {}
//}



//typealias OnInteractionListener = (post: Post)->Unit



class PostsAdapter(
    private val onInteractionListener: (Post) -> Unit,
) : ListAdapter<Post,PostViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)

    }


    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem

    }
}



