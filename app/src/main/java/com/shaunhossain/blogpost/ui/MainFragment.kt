package com.shaunhossain.blogpost.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shaunhossain.blogpost.MainActivity
import com.shaunhossain.blogpost.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val TAG = "MainFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        /*GlobalScope.launch(Dispatchers.IO) {
            val posts = Repository.getAllPosts()
            if(posts.isSuccessful){

                for (post in posts.body().toString()){
                    Log.d(TAG,posts.toString())

                }
            }
        }*/


    }

}