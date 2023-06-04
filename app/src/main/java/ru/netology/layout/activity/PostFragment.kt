package ru.netology.layout.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.layout.Until.ConvertNumber
import ru.netology.layout.activity.FeedFragment.Companion.idArg
import ru.netology.layout.activity.NewPostFragment.Companion.textArg
import ru.netology.layout.viewmodel.PostViewModel
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityCardPostFragmentBinding
import ru.netology.nmedia.databinding.FragmentPostBinding

class PostFragment : Fragment() {

    private val viewModel: PostViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostBinding.inflate(inflater, container, false)
        val id = arguments?.idArg


        viewModel.data.observe(viewLifecycleOwner) { posts ->
            binding.postContent.apply {
                posts.map { post ->
                    if (post.id.toInt() == id) {

                        author.text = post.author
                        publisher.text = post.published
                        content.text = post.content
                        like.text = ConvertNumber.counterDecimal(post.likes)
                        share.text = ConvertNumber.counterDecimal(post.shares)
                        view.text = ConvertNumber.counterDecimal(post.views)

                        like.isChecked = post.likeByMe

                        if (post.videoUrl == null) {
                            binding.postContent.video.visibility = View.GONE
                        } else {
                            binding.postContent.video.visibility = View.VISIBLE
                        }

                        like.setOnClickListener { viewModel.likeById(post.id) }
                        share.setOnClickListener { viewModel.shareById(post.id) }
                        view.setOnClickListener{viewModel.viewById(post.id)}
                        video.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.videoUrl))
                            startActivity(intent)
                        }
                        videoImage.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.videoUrl))
                            startActivity(intent)
                        }
                        requireActivity().onBackPressedDispatcher.addCallback(this) {
                            viewModel.save()
                        }
                        menu.setOnClickListener {
                            PopupMenu(it.context, it).apply {
                                inflate(R.menu.post_options)

                                setOnMenuItemClickListener { menuItem ->
                                    when (menuItem.itemId) {
                                        R.id.remove -> {
                                            viewModel.removeById(post.id)
                                            findNavController().navigateUp()
                                            true
                                        }
                                        R.id.edit -> {
                                            viewModel.edit(post)
                                            findNavController().navigate(
                                                R.id.action_postFragment_to_newPostFragment,
                                                Bundle().apply {
                                                    textArg = post.content
                                                }
                                            )
                                            true
                                        }
                                        else -> false
                                    }
                                }
                            }.show()
                        }
                    }
                }
            }
        }
        return binding.root
    }
}

private fun OnBackPressedDispatcher.addCallback(
    owner: ActivityCardPostFragmentBinding,
    onBackPressedCallback: () -> Unit,
) {
   onBackPressedCallback
}
