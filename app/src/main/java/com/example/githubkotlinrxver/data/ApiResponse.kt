package com.example.githubkotlinrxver.data

import com.google.gson.annotations.SerializedName

/**
 * response
 */
data class ApiResponse(
    val result: Any
)

class Userlist : ArrayList<UserListItem>()

data class UserListItem(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("login")
    val login: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean

//    val events_url: String,
//    val followers_url: String,
//    val following_url: String,
//    val gists_url: String,
//    val gravatar_id: String,
//    val html_url: String,
//    val node_id: String,
//    val organizations_url: String,
//    val received_events_url: String,
//    val repos_url: String,
//    val starred_url: String,
//    val subscriptions_url: String,
//    val type: String,
//    val url: String
)

data class UserInfo(
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("blog")
    val blog: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("location")
    val location: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean

//    val starred_url: String,
//    val subscriptions_url: String,
//    val twitter_username: Any,
//    val type: String,
//    val updated_at: String,
//    val url: String,
//    val company: Any,
//    val created_at: String,
//    val email: Any,
//    val events_url: String,
//    val followers: Int,
//    val followers_url: String,
//    val following: Int,
//    val following_url: String,
//    val gists_url: String,
//    val gravatar_id: String,
//    val hireable: Any,
//    val html_url: String,
//    val node_id: String,
//    val organizations_url: String,
//    val public_gists: Int,
//    val public_repos: Int,
//    val received_events_url: String,
//    val repos_url: String
)

