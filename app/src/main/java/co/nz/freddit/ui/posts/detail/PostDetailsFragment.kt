package co.nz.freddit.ui.posts.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import co.nz.freddit.core.helpers.autoCleared
import co.nz.freddit.data.common.Comment
import dagger.hilt.android.AndroidEntryPoint
import co.nz.freddit.data.remote.Result
import co.nz.freddit.databinding.PostDetailsBinding

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    private var binding: PostDetailsBinding by autoCleared()
    private val viewModel: PostDetailsViewModel by viewModels()
    private val args: PostDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.postTitle.text = args.title
        binding.postDescription.text = args.description
        viewModel.fetchComments(args.id).observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    if (it.data != null) {
                        binding.commentList.setData(it.data)
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.postDetailsContainer.visibility = View.VISIBLE
                }

                Result.Status.ERROR, Result.Status.NETWORK_ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Result.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.postDetailsContainer.visibility = View.GONE
                }
            }
        })
    }
}
