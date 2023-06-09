package ru.netology.layout.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.netology.layout.dto.Post
import ru.netology.layout.databinding.ActivityCardPostFragmentBinding

//
interface OnInteractionListener  {
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
    fun onViewPost(post: Post) {}
    fun onRemove(post: Post) {}
    fun onEdit(post: Post) {}
    fun onVideo(post: Post){}
    fun onPost(post: Post){}
}







class PostsAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Post,PostViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ActivityCardPostFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding,onInteractionListener)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)

    }


    class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
        override fun getChangePayload(oldItem: Post, newItem: Post): Any = Unit

    }

}





