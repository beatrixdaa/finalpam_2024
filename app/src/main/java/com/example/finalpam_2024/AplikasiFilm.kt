package com.example.finalpam_2024

import ContainerApp
import ContainerDataApp
import android.app.Application


class AplikasiFilm : Application(){

    lateinit var container: ContainerApp

    override fun onCreate(){
        super.onCreate()
        container = ContainerDataApp(this)
    }
}