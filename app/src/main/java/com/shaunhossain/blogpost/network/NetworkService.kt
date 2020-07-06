package com.shaunhossain.blogpost.network

import com.shaunhossain.blogpost.model.Posts
import com.shaunhossain.blogpost.model.PostsItem
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("/posts")
    suspend fun getPosts(): Response<Posts>
}