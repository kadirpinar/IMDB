package com.example.imdb.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.imdb.R
import com.example.imdb.model.Movies
import com.example.imdb.utilities.IMDBConstants
import com.squareup.picasso.Picasso


class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var movie: Movies
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var moviePoster: ImageView
    private lateinit var movieOverView : TextView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView= inflater.inflate(R.layout.movie_detail_fragment, container, false)
        toolbar=rootView.findViewById(R.id.toolbar)
        moviePoster=rootView.findViewById(R.id.movie_detail_image)
        movieOverView=rootView.findViewById(R.id.movie_description)
        val activity= rootView.context as AppCompatActivity

        activity.setSupportActionBar(toolbar);
        if (activity.supportActionBar != null){
            activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true);
            activity.supportActionBar!!.setDisplayShowHomeEnabled(true);

        }
        toolbar.setNavigationOnClickListener{
            activity.supportFragmentManager.popBackStack();

        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        val bundle = this.arguments
        if (bundle != null) {
            val movie: Movies? = bundle.getParcelable("movie")
            if (movie != null) {
                println(movie.getTitle())
                this.movie=movie
            }
        }
        toolbar.title=movie.getTitle()
        Picasso.get().load(IMDBConstants.IMAGE_URL +this.movie.getBackDropPath()).into(moviePoster)
        movieOverView.text=movie.getOverview()
    }


}