package ru.netology.layout.activity


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.layout.R
import ru.netology.layout.Until.AndroidUtils
import ru.netology.layout.databinding.ActivityMainBinding
import ru.netology.layout.dto.Post
import ru.netology.layout.viewmodel.PostViewModel
import ru.netology.nmedia.adapter.OnInteractionListener
import ru.netology.nmedia.adapter.PostsAdapter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()


        val adapter = PostsAdapter(onInteractionListener = object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }

            override fun onViewPost(post: Post) {
                viewModel.viewById(post.id)
            }


            override fun onRemove(post: Post) {
                viewModel.removeById(post.id)
            }


            override fun onEdit(post: Post) {
                viewModel.edit(post)
            }

        })

        binding.save.setOnClickListener {
            with(binding.content)
            {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }
        binding.clearEdit.setOnClickListener {
            with(binding.content) {
                viewModel.clearEdit()
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        binding.textChanged.setOnClickListener {
            with(binding.content)
            {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.changeContent(text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                binding.group.visibility = View.GONE
                return@observe
            }
            binding.group.visibility = View.VISIBLE

            with(binding.content) {
                requestFocus()
                setText(post.content)
            }





        }


    }

}









