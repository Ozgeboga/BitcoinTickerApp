package com.ob.bitcointicker.data.model

data class CommunityData(
    val facebook_likes: Any,
    val reddit_accounts_active_48h: Int,
    val reddit_average_comments_48h: Double,
    val reddit_average_posts_48h: Double,
    val reddit_subscribers: Int,
    val telegram_channel_user_count: Int,
    val twitter_followers: Int
)