package com.example.finalpam_2024.repositori

import com.example.finalpam_2024.data.Member
import kotlinx.coroutines.flow.Flow

interface RepositoriMember {


    fun getAllMemberStream(): Flow<List<Member>>

    fun getMemeberStream(id: Int): Flow<Member?>

    suspend fun insertMember(Member: Member)

    suspend fun deleteMember(Member: Member)

    suspend fun updateMember(Member: Member)
}