package com.example.imdb.model

import com.google.gson.annotations.SerializedName

class ResponseModel {
    private  var  page : Int?=null
    @SerializedName("total_pages")  private var totalPages : Int?=null
    @SerializedName("total_results") private var totalResults : Int?=null
    @SerializedName("results") private var movies :List<Movies>?=null

    constructor(page:Int,totalPages:Int,totalResults: Int){
        this.page=page
        this.totalPages=totalPages
        this.totalResults=totalResults
    }
    fun getResults():List<Movies>?{
        return movies
    }
    @Override
    override fun  toString():String
    {
        return "Response [page = $page, total_pages = $totalPages, results = $movies total_results = $totalResults,]"
    }
}