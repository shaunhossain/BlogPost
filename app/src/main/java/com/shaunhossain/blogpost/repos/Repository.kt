package com.shaunhossain.blogpost.repos

import com.shaunhossain.blogpost.model.Posts
import com.shaunhossain.blogpost.model.PostsItem
import com.shaunhossain.blogpost.network.NetworkInstrance
import retrofit2.Response

class Repository {
        suspend fun getAllPosts(): Response<Posts> = NetworkInstrance.api.getPosts()

}