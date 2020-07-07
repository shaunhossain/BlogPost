package com.shaunhossain.blogpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.shaunhossain.blogpost.repos.Repository
import com.shaunhossain.blogpost.ui.MainFragment
import com.shaunhossain.blogpost.ui.MainViewModel
import com.shaunhossain.blogpost.ui.ViewModelProviderFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        val repository= Repository()
        val viewModelProviderFactory = ViewModelProviderFactory(repository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(MainViewModel::class.java)

        //For debugging purpose
        /*GlobalScope.launch(Dispatchers.IO){

            val comments = getAllPosts2()
            if (comments.isSuccessful){
                Log.d(TAG,comments.body().toString())
            }
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }
}