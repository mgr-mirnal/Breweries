package com.example.brewery.data.remote

import com.example.brewery.domain.model.Brewery
import retrofit2.Response
import retrofit2.http.GET


interface BreweryApiService {

    @GET("/breweries")
    suspend fun getBreweries(): Response<Brewery>


}