package br.ifsp.moviemanager.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
    @PrimaryKey
    var name: String,
    var releaseYear: String,
    var producer: String,
    var duration: String,
    var genre: String,
    var note: String
)
