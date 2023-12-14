package br.ifsp.moviemanager.controller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.ifsp.moviemanager.model.database.MovieDatabase
import br.ifsp.moviemanager.model.entity.Movie
import br.ifsp.moviemanager.model.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel (application: Application): AndroidViewModel(application) {

    private val repository: MovieRepository
    var allContacts: LiveData<List<Movie>>
    lateinit var veiculo: LiveData<Movie>

    init {
        val dao =  MovieDatabase.getDatabase(application).movieDAO()
        repository = MovieRepository(dao)
        allContacts = repository.getAllContacts()
    }

    fun insert(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(movie)
    }

    fun update(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(movie)
    }

    fun delete(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(movie)
    }

}