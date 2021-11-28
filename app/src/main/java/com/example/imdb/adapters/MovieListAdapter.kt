package com.example.imdb.adapters

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R
import com.example.imdb.model.Movies
import com.example.imdb.ui.detail.MovieDetailFragment
import com.example.imdb.utilities.IMDBConstants.IMAGE_URL
import com.squareup.picasso.Picasso

class MovieListAdapter (private val movieList:List<Movies>): RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MoviesViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentItem = movieList[position]
        holder.movieTitle.text=movieList[position].getTitle()
        Picasso.get().load(IMAGE_URL+currentItem.getPosterPath()).into(holder.movieImage)
        holder.movieYear.text= movieList[position].getReleaseDate()?.subSequence(0,4)
        when {
            movieList[position].getVoteAverage()?.toInt()!! >=9 -> {
                holder.movieStar.setColorFilter(ContextCompat.getColor(holder.itemView.context,
                    R.color.green))
                holder.movieVote.setTextColor(Color.parseColor("#00FF00"));
            }
            movieList[position].getVoteAverage()?.toInt()!! in 7..9 -> {
                holder.movieStar.setColorFilter(ContextCompat.getColor(holder.itemView.context,
                    R.color.orange))
                holder.movieVote.setTextColor(Color.parseColor("#FFA500"));
            }
            movieList[position].getVoteAverage()?.toInt()!! <7 -> {
                holder.movieStar.setColorFilter(ContextCompat.getColor(holder.itemView.context,
                    R.color.red))
                holder.movieVote.setTextColor(Color.parseColor("#FF0000"));
            }
        }
        holder.movieVote.text=movieList[position].getVoteAverage().toString()+"/10"
        holder.movie.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable("movie", movieList[position])
            val movieDetailFragment = MovieDetailFragment()
            movieDetailFragment.arguments=bundle
           val activity= holder.itemView.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().apply {
                add(R.id.main_fragment, movieDetailFragment).addToBackStack(null)
                commit()
            }
        }
    }

    override fun getItemCount(): Int {
       return movieList.size
    }


    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieTitle: TextView = itemView.findViewById(R.id.movie_title)
        val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
        val movieYear: TextView = itemView.findViewById(R.id.movie_year)
        val movieVote :TextView = itemView.findViewById(R.id.movie_vote)
        val movieStar :ImageView = itemView.findViewById(R.id.movie_star)
        val movie :View = itemView.findViewById(R.id.movie_item)

    }
}