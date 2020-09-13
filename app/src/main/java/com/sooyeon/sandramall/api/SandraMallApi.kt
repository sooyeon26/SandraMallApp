package com.sooyeon.sandramall.api

import retrofit2.http.GET

interface SandraMallApi {

    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>


    companion object {
        val instance = ApiGenerator().generate(SandraMallApi::class.java)
    }
}