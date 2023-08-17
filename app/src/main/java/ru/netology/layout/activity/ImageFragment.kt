package ru.netology.layout.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import ru.netology.layout.BuildConfig.BASE_URL
import ru.netology.layout.R
import ru.netology.layout.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val binding = FragmentImageBinding.inflate(inflater, container, false)
        val attachmentUrl = arguments?.getString("image")

        binding.apply {
            imageFullScreen.visibility = View.GONE
            attachmentUrl?.let {
                val url = "${BASE_URL}/media/${it}"

                Glide.with(imageFullScreen)
                    .load(url)
                    .placeholder(R.drawable.ic_loading_24dp)
                    .error(R.drawable.ic_baseline_error_outline_24dp)
                    .timeout(10_000)
                    .into(imageFullScreen)
            }
            imageFullScreen.visibility = View.VISIBLE
        }

        binding.imageFullScreen.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}