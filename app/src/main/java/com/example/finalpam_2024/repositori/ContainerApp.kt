import android.content.Context
import com.example.finalpam_2024.data.DatabaseFilm
import com.example.finalpam_2024.repositori.OfflineRepositoriFilm
import com.example.finalpam_2024.repositori.RepositoriFilm

interface ContainerApp{
    val repositoriFilm: RepositoriFilm
}
class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriFilm: RepositoriFilm by lazy {
        OfflineRepositoriFilm(DatabaseFilm.getDatabase(context).FilmDao())
    }
}