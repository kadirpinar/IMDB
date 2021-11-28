package com.example.imdb.network

import com.example.imdb.model.ResponseModel
import com.example.imdb.utilities.IMDBConstants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

        @GET("/3/movie/popular?api_key=$API_KEY")
        fun  getMovies(@Query("page") page:Int,@Query("language") language:String): Call<ResponseModel>

}