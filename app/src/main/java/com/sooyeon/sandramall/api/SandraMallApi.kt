package com.sooyeon.sandramall.api

import com.sooyeon.sandramall.api.request.SignupRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SandraMallApi {

    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    @POST("api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest): ApiResponse<Void>

    companion object {
        val instance = ApiGenerator().generate(SandraMallApi::class.java)
    }
}