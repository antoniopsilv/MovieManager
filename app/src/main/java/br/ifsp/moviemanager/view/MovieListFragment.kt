package br.ifsp.moviemanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
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
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_movieListFragment_to_registerFragment)
        }
        configureRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.register_menu, menu)

/*                val searchView = menu.findItem(R.id.).actionView as SearchView
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        veiculoAdapter.filter.filter(p0)
                        return true
                    }
                })*/
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                TODO("Not yet implemented")
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
    private fun configureRecyclerView(){
        viewModel= ViewModelProvider(this).get(MovieViewModel::class.java)
        viewModel.allContacts.observe(viewLifecycleOwner) {list ->
            list?.let{
                movieAdapter.updateList(list as ArrayList<Movie>)
            }
        }
        val recyclerView  = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter

        val listener = object : MovieAdapter.MovieListener {
            override fun onItemClick(pos: Int) {
                val m = movieAdapter.moviesListFilterable[pos]

                val bundle = Bundle()
                bundle.putInt("id", m.id)

                findNavController().navigate(
                    R.id.action_movieListFragment_to_detailFragment,
                    bundle
                )
            }
        }
        movieAdapter.setClickListener(listener)
    }
}
