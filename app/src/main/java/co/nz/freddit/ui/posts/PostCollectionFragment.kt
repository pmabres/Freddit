package co.nz.freddit.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import co.nz.freddit.core.base.BaseNavigationFragment
import co.nz.freddit.core.helpers.autoCleared
import co.nz.freddit.data.remote.Result
import co.nz.freddit.databinding.PostCollectionBinding
import co.nz.freddit.ui.adapters.PostsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostCollectionFragment : BaseNavigationFragment() {

    private var binding: PostCollectionBinding by autoCleared()
    private val viewModel: PostCollectionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.posts.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) binding.postList.setData(it.data)
                }
                Result.Status.ERROR, Result.Status.NETWORK_ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Result.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
        binding.postList.setOnItemClickListener { view, position, item ->
            navigate(PostCollectionFragmentDirections.actionPostCollectionFragmentToPostDetailsFragment(item.id, item.title, item.body))
        }

    }
}
