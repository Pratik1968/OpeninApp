package com.pratikshekhar.openinapp.model

data class Link(
    val app: String="",
    val created_at: String="",
    val domain_id: String="",
    val is_favourite: Boolean= true,
    val original_image: String="",
    val smart_link: String="",
    val thumbnail: String?=null,
    val times_ago: String="",
    val title: String="",
    val total_clicks: Int=0,
    val url_id: Int=0,
    val url_prefix: String? =null,
    val url_suffix: String="",
    val web_link: String=""
)