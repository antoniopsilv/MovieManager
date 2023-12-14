package br.ifsp.moviemanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.ifsp.moviemanager.controller.MovieViewModel
import br.ifsp.moviemanager.databinding.FragmentDetailBinding
import br.ifsp.moviemanager.model.entity.Movie

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

        val id: String = requireArguments().getChar(id.toString()).toString()
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
    }
}