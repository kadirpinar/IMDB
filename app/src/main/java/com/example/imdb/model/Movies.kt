package com.example.imdb.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import com.example.imdb.utilities.IMDBConstants.IMAGE_URL
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

@SuppressLint("ParcelCreator")
open class Movies : Parcelable,RealmObject{
    private var adult:Boolean?=null
    @SerializedName("backdrop_path")  private var backdropPath:String?=null
    private var id : Int?=null
    @SerializedName("original_language") private var originalLanguage:String?=null
    @SerializedName("original_title") private var originalTitle:String?=null
    private var overview:String?=null
    private var popularity:Double?=null
    @SerializedName("poster_path") private var posterPath:String?=null
    @SerializedName("release_date") private var releaseDate:String?=null
    private var title:String?=null
    private var video:Boolean?=null
    @SerializedName("vote_average") private var voteAverage:Double?=null
    @SerializedName("vote_count") private var voteCount:Int?=null

    constructor(adult:Boolean,backdropPath:String,id : Int,originalLanguage:String,originalTitle:String,overview:String,popularity:Double,
                posterPath:String,releaseDate:String,title:String,video:Boolean,voteAverage:Double,voteCount:Int){
        this.adult=adult
        this.backdropPath=backdropPath
        this.id=id
        this.originalLanguage=originalLanguage
        this.originalTitle=originalTitle
        this.overview=overview
        this.popularity=popularity
        this.posterPath=posterPath
        this.releaseDate=releaseDate
        this.title=title
        this.video=video
        this.voteAverage=voteAverage
        this.voteCount=voteCount
    }

    constructor(){

    }

    fun getAdult(): Boolean? {
        return adult
    }
        fun getBackDropPath(): String? {
        return backdropPath
    }
    fun getId(): Int? {
        return id
    }
    fun getOriginalLanguage(): String? {
        return originalLanguage
    }
    fun getOriginalTitle(): String? {
        return originalTitle
    }
    fun getOverview(): String? {
        return overview
    }
    fun getPopularity(): Double? {
        return popularity
    }
    fun getPosterPath(): String? {
        return posterPath
    }
    fun getReleaseDate(): String? {
        return releaseDate
    }
    fun getTitle(): String? {
        return title
    }
    fun getVideo(): String? {
        return title
    }
    fun getVoteAverage(): Double? {
        return voteAverage
    }
    fun getVoteCount(): Int? {
        return voteCount
    }
    @Override
    override fun  toString():String
    {
        return "Movies [adult = $adult, backdrop_path = $backdropPath, id = $id, original_language= $originalLanguage, original_title=$originalTitle, overview=$overview, popularity=$popularity, poster_path=$posterPath, release_date=$releaseDate, title=$title, video=$video, vote_average=$voteAverage, vote_count=$voteCount]"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
    }
}
