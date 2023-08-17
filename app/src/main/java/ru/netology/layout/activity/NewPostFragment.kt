package ru.netology.layout.activity

import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import ru.netology.layout.R
import ru.netology.layout.until.AndroidUtils
import ru.netology.layout.until.StringArg
import ru.netology.layout.viewmodel.PostViewModel
import ru.netology.layout.databinding.FragmentNewPostBinding

class NewPostFragment : Fragment() {

    companion object {
        var Bundle.textArg: String? by StringArg
    }
    private var fragmentBinding: FragmentNewPostBinding? = null

    private val viewModel: PostViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentNewPostBinding.inflate(
            inflater,
            container,
            false
        )
        fragmentBinding = binding

        arguments?.textArg.let(binding.content::setText)

        val photoLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                when (it.resultCode) {
                    ImagePicker.RESULT_ERROR -> {
                        Toast.makeText(requireContext(), "Image pick Error", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else -> {
                        val uri: Uri? = it.data?.data
                        viewModel.changePhoto(uri, uri?.toFile())
                    }
                }
            }

        binding.pickPhoto.setOnClickListener {
            ImagePicker.Builder(this)
                .galleryOnly()
                .maxResultSize(2048, 2048)
                .createIntent(photoLauncher::launch)
        }

        binding.takePhoto.setOnClickListener {
            ImagePicker.Builder(this)
                .cameraOnly()
                .maxResultSize(2048, 2048)
                .createIntent(photoLauncher::launch)
        }

        binding.deletePhoto.setOnClickListener {
            viewModel.changePhoto(null, null)
        }


        viewModel.postCreated.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
        viewModel.photo.observe(viewLifecycleOwner) {
            if (it?.uri == null) {
                binding.photoLayout.visibility = View.GONE
                return@observe
            }

            binding.photoLayout.visibility = View.VISIBLE
            binding.photo.setImageURI(it.uri)
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.create_post_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                when (menuItem.itemId) {
                    R.id.save -> {
                        viewModel.changeContent(binding.content.text.toString())
                        viewModel.save()
                        AndroidUtils.hideKeyboard(requireView())
                        true
                    }
                    else -> false
                }
        }, viewLifecycleOwner)
        return binding.root
    }
    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}