package com.pratikshekhar.openinapp.model

data class Api_Response(
    val applied_campaign: Int=0,
    val `data`: Data=Data(emptyList(), emptyMap(), emptyList(), emptyList()),
    val extra_income: Double=0.0,
    val links_created_today: Int=0,
    val message: String="",
    val startTime: String="",
    val status: Boolean=false,
    val statusCode: Int=0,
    val support_whatsapp_number: String="",
    val today_clicks: Int=0,
    val top_location: String="",
    val top_source: String="",
    val total_clicks: Int=0,
    val total_links: Int=0
)