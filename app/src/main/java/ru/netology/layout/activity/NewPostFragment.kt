package ru.netology.layout.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.layout.Until.AndroidUtils
import ru.netology.layout.Until.StringArg
import ru.netology.layout.viewmodel.PostViewModel
import ru.netology.nmedia.databinding.FragmentNewPostBinding

class NewPostFragment : Fragment() {private val viewModel: PostViewModel by viewModels(
    ownerProducer = ::requireParentFragment
)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewPostBinding.inflate(
            inflater,
            container,
            false
        )

        arguments?.textArg?.let {
            binding.edit.setText(it)
        }


        binding.edit.requestFocus()
        binding.ok.setOnClickListener {
            viewModel.changeContent(binding.edit.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }
        return binding.root
    }


    companion object {
        var Bundle.textArg: String? by StringArg
    }


}