package com.pratikshekhar.openinapp.Network

import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor(private val token:String):Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization","Bearer ${token}").build()
        return chain.proceed(request)
    }
}