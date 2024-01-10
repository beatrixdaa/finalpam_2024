package com.example.finalpam_2024.repositori

import android.content.Context
import com.example.finalpam_2024.data.DatabaseFilm

interface ContainerApp {
    val repositoriFilm : RepositoriFilm

    val repositoriMember : RepositoriMember
}

class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriFilm: RepositoriFilm by lazy {
        OfflineRepositori(DatabaseFilm.getDatabase(context).FilmDao())
    }

}