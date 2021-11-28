package com.example.imdb.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R
import com.example.imdb.adapters.MovieListAdapter
import com.example.imdb.model.Movies

class MainPageFragment : Fragment() {

    companion object {
        fun newInstance() = MainPageFragment()
    }

    private lateinit var viewModel: MainPageViewModel
    private lateinit var moviesRecylerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var addButton: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.main_page_fragment, container, false)
        moviesRecylerView = rootView.findViewById<RecyclerView>(R.id.movies_recycler_view)
        addButton = rootView.findViewById<View>(R.id.add_button)
        moviesRecylerView.adapter=MovieListAdapter(listOf<Movies>())
        viewModel = ViewModelProvider(this).get(MainPageViewModel::class.java)
        viewModel.progressBar = rootView.findViewById<ProgressBar>(R.id.progress_bar)
        layoutManager = LinearLayoutManager(rootView.context)
        moviesRecylerView.layoutManager=layoutManager
        moviesRecylerView.setHasFixedSize(true)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getDataFromApi(1)
        addButton.setOnClickListener{
            println("Button Clicked")
        }
        moviesRecylerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy>0){
                    var visibleItemCount=layoutManager.childCount
                    val postVisibleItemCount= layoutManager.findFirstVisibleItemPosition()
                    val total = layoutManager.itemCount
                    if((visibleItemCount+postVisibleItemCount)>=total){
                        if(!viewModel.requesting) {
                            viewModel.page++
                            viewModel.getDataFromApi( viewModel.page)
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
        viewModel.getMoviesListObserver().observe(viewLifecycleOwner, Observer {
                it->if(moviesRecylerView.adapter?.itemCount==0){moviesRecylerView.adapter= MovieListAdapter(it)}else{
            moviesRecylerView.adapter!!.notifyDataSetChanged()
        }
        })
    }

}