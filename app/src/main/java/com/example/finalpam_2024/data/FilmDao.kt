package com.example.finalpam_2024.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(film: Film)

    @Update
    suspend fun update(film: Film)

    @Delete
    suspend fun delete(film: Film)

    @Query("SELECT * from tblFilm WHERE id = :id")
    fun getFilm(id: Int): Flow<Film>

    @Query("SELECT * from tblFilm ORDER BY judul ASC")
    fun getAllFilm(): Flow<List<Film>>
}

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(member: Member)

    @Delete
    suspend fun delete(member: Member)

    @Query("SELECT * from tblMember")
    fun getMember(): Flow<List<Member>>
}
