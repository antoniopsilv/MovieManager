package br.ifsp.moviemanager.model.repository

import androidx.lifecycle.LiveData
import br.ifsp.moviemanager.model.dao.MovieDAO
import br.ifsp.moviemanager.model.entity.Movie

class MovieRepository (private val movieDAO: MovieDAO) {

    suspend fun insert(movie: Movie) {
        movieDAO.insert(movie)
    }

    suspend fun update(movie: Movie) {
        movieDAO.update(movie)
    }

    suspend fun delete(movie: Movie) {
        movieDAO.delete(movie)
    }

    fun getAllContacts(): LiveData<List<Movie>> {
        return movieDAO.getAllContacts()
    }

    fun getContactById(id: String): LiveData<Movie>{
        return movieDAO.getContactById(id)
    }

}