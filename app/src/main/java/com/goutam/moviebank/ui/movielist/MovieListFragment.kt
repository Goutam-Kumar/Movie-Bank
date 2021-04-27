package com.goutam.moviebank.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.goutam.moviebank.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {
    lateinit var bindingMovieList: FragmentMovieListBinding
    lateinit var listAdapter: MovieListAdapter
    lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        bindingMovieList = FragmentMovieListBinding.inflate(inflater, container, false)
        return bindingMovieList.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        bindingMovieList.rcvMovieList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            listAdapter = MovieListAdapter()
            addItemDecoration(GridSpacingItemDecoration(2, 25, false))
            adapter = listAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            movieList.observe(viewLifecycleOwner, Observer {
                listAdapter.bindMovieList(it)
            })

            serviceException.observe(viewLifecycleOwner, Observer {
                showExitBookingDialogue(it)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllPopularMovies()
    }

    private fun showExitBookingDialogue(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .setOnCancelListener { }
            .create().run {
                setCanceledOnTouchOutside(false)
                show()
            }
    }
}
