package com.example.finalpam_2024.repositori

import com.example.finalpam_2024.data.Member
import kotlinx.coroutines.flow.Flow

interface RepositoriMember {

    fun getMemberStream(id: Int): Flow<Member?>
}