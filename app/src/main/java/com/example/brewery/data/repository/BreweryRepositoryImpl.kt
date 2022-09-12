package com.example.brewery.data.repository

import android.util.Log
import com.example.brewery.common.ResponseState
import com.example.brewery.data.remote.BreweryApiService
import com.example.brewery.domain.repository.Repository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "Repository"

class BreweryRepositoryImpl @Inject constructor(private val service: BreweryApiService) : Repository {

    override suspend fun getBrewery() = flow {
        try{
            val response = service.getBreweries()
            if(response.isSuccessful) {
                response.body()?.let {
                    Log.d(TAG, "getBrewery: Success")
                    emit(ResponseState.SUCCESS(it))
                } ?: throw Exception("Error Null")
            }else {
                Log.d(TAG, "getBrewery: Failure")
                throw Exception("Error Failure")
            }
        } catch (e: Exception){
            Log.d(TAG, "getBrewery: Failure: "+ e.message)
            emit(ResponseState.ERROR(e))
        }

    }


}