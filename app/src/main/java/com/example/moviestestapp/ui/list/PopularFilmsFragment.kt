package com.example.moviestestapp.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviestestapp.databinding.FragmentPopularFilmsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFilmsFragment : Fragment() {

    private lateinit var binding: FragmentPopularFilmsBinding

    private val viewModel by viewModels<PopularFilmsViewModel>()

    private val rvAdapter = PopularFilmsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularFilmsBinding.inflate(inflater, container, false)
        val rvLayoutManager = GridLayoutManager(context, 1)
        binding.recyclerView.apply {
            layoutManager = rvLayoutManager
            adapter = rvAdapter.withLoadStateFooter(MoviesLoaderStateAdapter())
        }
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            rvAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        })

        rvAdapter.addLoadStateListener { state: CombinedLoadStates ->
            binding.recyclerView.isVisible = state.refresh != LoadState.Loading
            binding.progress.isVisible = state.refresh == LoadState.Loading
        }

        return binding.root
    }


}