package ru.netology.layout.activity


import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.layout.R
import ru.netology.layout.databinding.ActivityMainBinding
import ru.netology.layout.dto.Post
import ru.netology.layout.viewmodel.PostViewModel
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter


class MainActivity : AppCompatActivity(), OnInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()


        val adapter = PostsAdapter(object :OnInteractionListener{
            override fun onLike(post: Post){
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }

            override fun onViewPost(post: Post) {
                viewModel.viewById(post.id)
            }

        })




        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

    }

}






