package com.example.finalpam_2024.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tblFilm")
data class Film(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val judul : String,
    val genre : String,
    val tahun_rilis : String,
)
