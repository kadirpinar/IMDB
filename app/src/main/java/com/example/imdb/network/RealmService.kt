package com.example.dh.network

import androidx.lifecycle.MutableLiveData
import com.example.imdb.model.Movies
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.where

object RealmService {
    private  val config:RealmConfiguration
    private  val realm : Realm
    val moviesLive = MutableLiveData<List<Movies>>()

    init {
        config = RealmConfiguration.Builder().name( "imdb.realm").allowWritesOnUiThread(true).allowQueriesOnUiThread(true).build()
        realm = Realm.getInstance(config)
    }

    fun insertData(movies:List<Movies>){
        realm.executeTransaction { transactionRealm ->
            transactionRealm.insert(movies)
        }
    }
    fun getAllData():List<Movies>{
        val movies : List<Movies> = realm.where<Movies>().findAll()
        moviesLive.postValue(movies)
        return movies
    }
    fun getData(currentItem:Movies): Movies? {
        val movie :List<Movies> = realm.where<Movies>().equalTo("Id",currentItem.getId()).findAll()
        if(movie.isEmpty()){
            return null
        }else{
            return movie.first()
        }}
    fun removeData(currentItem:Movies) {

        realm.executeTransaction { transactionRealm ->
            val movie : RealmResults<Movies?>? = transactionRealm.where<Movies>().equalTo("Id",currentItem.getId()).findAll()
            movie?.deleteAllFromRealm()
        }

    }
    fun removeAllData() {
        realm.executeTransaction { transactionRealm ->
            realm.deleteAll()
        }
    }

}