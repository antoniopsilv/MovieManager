package br.ifsp.moviemanager.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var releaseYear: String,
    var producer: String,
    var duration: String,
    var genre: String,
    var note: String,
    var watched: Boolean = false,
)
