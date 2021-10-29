package com.example.moviestestapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviestestapp.data.Result
import com.example.moviestestapp.databinding.ItemBinding

class PopularFilmsAdapter: PagingDataAdapter<Result, MoviesViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context))).apply {
            itemView.setOnClickListener {
                var navController: NavController? = null
                navController = Navigation.findNavController(itemView)
                navController.navigate(PopularFilmsFragmentDirections.actionPopularFilmsFragmentToDetailsFragment(getItem(adapterPosition)!!.id.toLong()))

            }
        }

    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }
}

class MoviesViewHolder(private val binding: ItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Result) {
        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500${movie.backdrop_path}").into(binding.moviePhoto)
        binding.movieTitle.text = movie.title
    }
}