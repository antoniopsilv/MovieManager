package br.ifsp.moviemanager.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.ifsp.moviemanager.model.entity.Movie

@Dao
interface MovieDAO {

    @Insert
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * FROM movie ORDER BY name")
    fun getAllContacts(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE id=:id")
    fun getContactById(id: Int): LiveData<Movie>

}