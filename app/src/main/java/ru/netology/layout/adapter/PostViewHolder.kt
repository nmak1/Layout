package ru.netology.layout.adapter

import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.netology.layout.until.ConvertNumber
import ru.netology.layout.dto.Post
import ru.netology.layout.R
import ru.netology.layout.databinding.ActivityCardPostFragmentBinding

class PostViewHolder(
    private val binding : ActivityCardPostFragmentBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            publisher.text = post.published
            content.text = post.content
            like.isChecked = post.likeByMe
            like.text = "${post.likes}"
            share.text = ConvertNumber.counterDecimal(post.shares)
            view.text = ConvertNumber.counterDecimal(post.views)
            video.isVisible = !post.videoUrl.isNullOrBlank()
            videoImage.isVisible = !post.videoUrl.isNullOrBlank()


            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_options)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }

                    }

                }.show()

            }
            like.setOnClickListener {
                onInteractionListener.onLike(post)

            }
            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }
            view.setOnClickListener {
                onInteractionListener.onViewPost(post)

            }
            video.setOnClickListener {

                onInteractionListener.onVideo(post)

            }
        }


    }
}







