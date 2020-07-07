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
import java.io.IOException


class MainViewModel(
    val repository: Repository) : ViewModel() {
    private val TAG = "PostViewModel"

    val posts: MutableLiveData<Resource<Posts>>? = MutableLiveData()
    var postResponse: Posts?= null

    init {
        getPost()
    }

    fun getPost() = viewModelScope.launch {
        safePostsCall()
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

    private suspend fun safePostsCall(){
        posts?.postValue(Resource.Loading())
        try {
                val response = repository.getAllPosts()
                posts?.postValue(handlePosts(response))
        } catch (t : Throwable){
            when (t) {
                is IOException -> posts!!.postValue(Resource.Error("Network Failure"))
                else -> posts!!.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

}