package com.example.moviestestapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.moviestestapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.getDetailsMovie(args.id.toInt())
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            binding.apply {
                title.text = it.title
                description.text = it.overview
            }
            Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500${it.backdrop_path}").into(binding.poster)
        })
        return binding.root
    }

}