package br.ifsp.moviemanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.ifsp.moviemanager.R
import br.ifsp.moviemanager.databinding.FragmentMovieListBinding


class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
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
        return root
    }
}