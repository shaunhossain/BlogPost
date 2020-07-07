package com.shaunhossain.blogpost.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaunhossain.blogpost.MainActivity
import com.shaunhossain.blogpost.R
import com.shaunhossain.blogpost.adapter.PostAdapter
import com.shaunhossain.blogpost.repos.Repository
import com.shaunhossain.blogpost.utils.Resource
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    lateinit var postsAdapter: PostAdapter

    private val TAG = "MainFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()



        viewModel.posts?.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { postsResponse ->
                        postsAdapter.differ.submitList(postsResponse.toList())
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    Toast.makeText(activity, "Data loading", Toast.LENGTH_LONG).show()
                }
            }
        })


    }

    private fun setupRecyclerView() {
        postsAdapter = PostAdapter()
        recyclerview_posts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}