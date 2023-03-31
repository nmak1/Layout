package ru.netology.layout.activity


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.layout.R
import ru.netology.layout.databinding.ActivityMainBinding
import ru.netology.layout.Until.ConvertNumber.counterDecimal
import ru.netology.layout.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {

    val viewModel: PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                publisher.text = post.published
                content.text = post.content

                if (post.likeByMe) {
                    like.setImageResource(R.drawable.baseline_favorite_red_24)
                } else like.setImageResource(R.drawable.baseline_favorite_border_24)

                countlike.text = counterDecimal(post.likes)
                counsher.text = counterDecimal(post.sheres)
                countview.text = counterDecimal(post.views)


            }
        }

        listeneres(binding)


    }

    fun listeneres(binding: ActivityMainBinding) {
        binding.like.setOnClickListener {
            viewModel.like()

        }
        binding.share.setOnClickListener {
            viewModel.shere()
        }
        binding.view.setOnClickListener {
            viewModel.view()
        }
    }

}





