package com.sooyeon.sandramall.api

import com.sooyeon.sandramall.api.response.ApiResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface SandraMallRefreshApi {
    @POST("/api/v1/refresh_token")
    suspend fun refreshToken(
        @Query("grant_type") grantType: String = "refresh_token"
    ): ApiResponse<String>

    companion object {
        val instance = ApiGenerator().generateRefreshClient(SandraMallRefreshApi::class.java)
    }

}