package com.example.finalpam_2024

import android.app.Application
import com.example.finalpam_2024.repository.ContainerApp
import com.example.finalpam_2024.repository.ContainerDataApp

class AplikasiFilm : Application(){

    lateinit var container: ContainerApp

    override fun onCreate(){
        super.onCreate()
        container = ContainerDataApp(this)
    }
}