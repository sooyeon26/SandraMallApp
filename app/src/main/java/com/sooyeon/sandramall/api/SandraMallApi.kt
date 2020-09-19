package com.sooyeon.sandramall.api

import com.sooyeon.sandramall.api.request.SigninRequest
import com.sooyeon.sandramall.api.request.SignupRequest
import com.sooyeon.sandramall.api.response.ApiResponse
import com.sooyeon.sandramall.api.response.SigninResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SandraMallApi {

    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    @POST("api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest): ApiResponse<Void>

    @POST("/api/v1/signin")
    suspend fun signin(@Body signinRequest: SigninRequest): ApiResponse<SigninResponse>

    companion object {
        val instance = ApiGenerator().generate(SandraMallApi::class.java)
    }
}