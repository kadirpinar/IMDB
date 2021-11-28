package com.example.imdb.ui.main

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dh.network.RealmService
import com.example.imdb.model.Movies
import com.example.imdb.model.ResponseModel
import com.example.imdb.network.MovieService
import com.example.imdb.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPageViewModel : ViewModel() {
    private val retrofit = RetroInstance()
    var  movieService = retrofit.getRetrofit()?.create(MovieService::class.java)
    val movies = MutableLiveData<List<Movies>>()
    val moviesList = mutableListOf<Movies>()
    var  requesting = false
    var page = 1
    var  progressBar: ProgressBar? =null

    fun  getMoviesListObserver(): MutableLiveData<List<Movies>> {
        return movies
    }

    fun getDataFromApi(page:Int){
        if(moviesList.size<99) {
            progressBar?.visibility = View.VISIBLE
            requesting = true
            movieService?.getMovies(page, "en-En")?.enqueue(object : Callback<ResponseModel> {
                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    requesting = false
                    progressBar?.visibility = View.INVISIBLE
                    movies.postValue(RealmService.getAllData())
                }

                override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                    requesting = false
                    if (response.isSuccessful) {
                        RealmService.removeAllData()
                        moviesList.addAll(response.body()?.getResults()!!)
                        RealmService.insertData(moviesList)
                        movies.postValue(moviesList)
                        progressBar?.visibility = View.INVISIBLE;
                    }
                }
            })

        }
    }
}