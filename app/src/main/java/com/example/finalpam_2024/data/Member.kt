package com.example.finalpam_2024.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblMember")
data class Member(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama : String,
    val email : String,
    val kata_sandi : String
)
