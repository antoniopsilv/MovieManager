package br.ifsp.moviemanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.ifsp.moviemanager.R
import br.ifsp.moviemanager.controller.MovieViewModel
import br.ifsp.moviemanager.databinding.FragmentDetailBinding
import br.ifsp.moviemanager.model.entity.Movie
import com.google.android.material.snackbar.Snackbar

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var movie: Movie

    lateinit var nomeEditText: EditText
    lateinit var anoLancamentoEditText: EditText
    lateinit var producaoEditText: EditText
    lateinit var duracaoEditText: EditText
    lateinit var generoEditText: EditText
    lateinit var notaEditText: EditText

    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nomeEditText = binding.commonLayout.editTextNome
        anoLancamentoEditText = binding.commonLayout.editTextAnoLancamento
        producaoEditText = binding.commonLayout.editTextProducao
        duracaoEditText = binding.commonLayout.editTextDuracao
        generoEditText = binding.commonLayout.editTextGenero
        notaEditText = binding.commonLayout.editTextNota

        val id = requireArguments().getInt("id")
        viewModel.getContactById(id)
        viewModel.movie.observe(viewLifecycleOwner) { result ->
            result?.let {
                movie = result

                nomeEditText.setText(movie.name)
                anoLancamentoEditText.setText(movie.releaseYear)
                producaoEditText.setText(movie.producer)
                duracaoEditText.setText(movie.duration)
                generoEditText.setText(movie.genre)
                notaEditText.setText(movie.note)
            }
        }
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.register_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: android.view.MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.action_alterarFilme -> {
                            movie.name = nomeEditText.text.toString()
                            movie.releaseYear = anoLancamentoEditText.text.toString()
                            movie.producer = producaoEditText.text.toString()
                            movie.duration = duracaoEditText.text.toString()
                            movie.genre = generoEditText.text.toString()
                            movie.note = notaEditText.text.toString()

                            viewModel.update(movie)
                            Snackbar.make(binding.root, "Filme alterado", Snackbar.LENGTH_SHORT)
                                .show()
                            findNavController().popBackStack()
                            true
                    }
                    R.id.action_excluirFilme -> {

                        viewModel.delete(movie)
                        Snackbar.make(binding.root, "Filme apagado", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}