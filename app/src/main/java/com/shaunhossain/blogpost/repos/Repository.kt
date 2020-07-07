package com.shaunhossain.blogpost.repos

import com.shaunhossain.blogpost.model.Posts
import com.shaunhossain.blogpost.model.PostsItem
import com.shaunhossain.blogpost.network.NetworkInstrance
import retrofit2.Response

class Repository {
        /*companion object{

                suspend fun getAllPosts2(): Response<Posts> = NetworkInstrance.api.getPosts()
        }*/
        suspend fun getAllPosts(): Response<Posts> = NetworkInstrance.api.getPosts()

}