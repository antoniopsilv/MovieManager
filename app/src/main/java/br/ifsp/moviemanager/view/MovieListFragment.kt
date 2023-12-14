package br.ifsp.moviemanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.ifsp.moviemanager.R
import br.ifsp.moviemanager.controller.MovieViewModel
import br.ifsp.moviemanager.databinding.FragmentMovieListBinding
import br.ifsp.moviemanager.model.entity.Movie
import br.ifsp.moviemanager.view.Adapter.MovieAdapter


class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_movieListFragment_to_registerFragment) }
        configureRecyclerView()
        return root
    }

    private fun configureRecyclerView()
    {
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.allContacts.observe(viewLifecycleOwner) { list ->
            list?.let {
                movieAdapter.updateList(list as ArrayList<Movie>)
            }
        }
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
    }
}
