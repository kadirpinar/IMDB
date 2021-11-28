package com.example.imdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.imdb.ui.main.MainPageFragment
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(applicationContext)
        val mainPageFragment= MainPageFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment, mainPageFragment)
            commit()
        }
    }
}