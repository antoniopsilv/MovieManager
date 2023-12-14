package br.ifsp.moviemanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.ifsp.moviemanager.R
import br.ifsp.moviemanager.controller.MovieViewModel
import br.ifsp.moviemanager.databinding.FragmentRegisterBinding
import br.ifsp.moviemanager.model.entity.Movie
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.register_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.action_alterarFilme-> {
                        val nome = binding.commonLayout.editTextNome.text.toString()
                        val anoLancamento = binding.commonLayout.editTextAnoLancamento.text.toString()
                        val producao = binding.commonLayout.editTextProducao.text.toString()
                        val duracao = binding.commonLayout.editTextDuracao.text.toString()
                        val genero = binding.commonLayout.editTextGenero.text.toString()
                        val nota = binding.commonLayout.editTextNota.text.toString()

                        val movie = Movie(0,nome, anoLancamento,producao, duracao, genero, nota)
                        viewModel.insert(movie)
                            Snackbar.make(binding.root, "Filme inserido", Snackbar.LENGTH_SHORT)
                                .show()
                            findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
