package com.shaunhossain.blogpost.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaunhossain.blogpost.model.Posts
import com.shaunhossain.blogpost.model.PostsItem
import com.shaunhossain.blogpost.repos.Repository
import com.shaunhossain.blogpost.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class MainViewModel(
    val repository: Repository) : ViewModel() {
    private val TAG = "PostViewModel"

    val posts: LiveData<Resource<Posts>>? = MutableLiveData()
    var postResponse: Posts?= null

    fun getPost() = viewModelScope.launch {
        repository.getAllPosts()
    }

    private fun handlePosts(resource: Response<Posts>): Resource<Posts> {
        if (resource.isSuccessful){
            resource.body()?.let { resultResponse ->

                if (postResponse == null){
                    postResponse = resultResponse
                }
                return Resource.Success(postResponse ?: resultResponse)
            }
        }
        return Resource.Error(resource.message())
    }

}