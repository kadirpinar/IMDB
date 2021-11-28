package com.example.imdb.utilities

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.imdb.R
import java.util.jar.Attributes

class CustomButtonView(context: Context, attributes: AttributeSet) :
    LinearLayout(context, attributes) {
    private lateinit var textView: TextView
    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_button_view, this, true)
    }
}