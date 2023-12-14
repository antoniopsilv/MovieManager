package br.ifsp.moviemanager.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.ifsp.moviemanager.model.dao.MovieDAO
import br.ifsp.moviemanager.model.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null
        fun getDatabase(context: Context): MovieDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "DBMoviesRoom.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}