package com.example.finalpam_2024.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(member: Member)

    @Delete
    suspend fun delete(member: Member)

    @Query("SELECT * from tblMember")
    fun getMember(): Flow<List<Member>>

}