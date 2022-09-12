package com.example.brewery.domain.repository

import com.example.brewery.common.ResponseState
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getBrewery(): Flow<ResponseState>
}