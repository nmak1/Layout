package ru.netology.layout.adapter

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.layout.R
import ru.netology.layout.Until.ConvertNumber
import ru.netology.layout.databinding.CardPostBinding
import ru.netology.layout.dto.Post
import ru.netology.nmedia.adapter.OnInteractionListener

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            publisher.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likeByMe) R.drawable.baseline_favorite_red_24 else R.drawable.baseline_favorite_border_24
            )
            countlike.text = ConvertNumber.counterDecimal(post.likes)
            counsher.text = ConvertNumber.counterDecimal(post.shares)
            countview.text = ConvertNumber.counterDecimal(post.views)

            like.setOnClickListener {
                onInteractionListener.onLike(post)

            }
            share.setOnClickListener {
                onInteractionListener.onShare(post)
            }
            view.setOnClickListener {
                onInteractionListener.onViewPost(post)

            }
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.post_options)
                    setOnMenuItemClickListener { item->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true}
                            else-> false
                        }

                    }

                }.show()

            }
        }


    }
}







