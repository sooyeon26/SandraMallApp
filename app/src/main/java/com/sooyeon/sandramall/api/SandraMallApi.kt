package com.sooyeon.sandramall.api

import com.sooyeon.sandramall.api.request.ProductRegistrationRequest
import com.sooyeon.sandramall.api.request.SigninRequest
import com.sooyeon.sandramall.api.request.SignupRequest
import com.sooyeon.sandramall.api.response.ApiResponse
import com.sooyeon.sandramall.api.response.SigninResponse
import com.sooyeon.sandramall.product.response.ProductImageUploadResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface SandraMallApi {

    @GET("/api/v1/hello")
    suspend fun hello(): ApiResponse<String>

    @POST("api/v1/users")
    suspend fun signup(@Body signupRequest: SignupRequest): ApiResponse<Void>

    @POST("/api/v1/signin")
    suspend fun signin(@Body signinRequest: SigninRequest): ApiResponse<SigninResponse>

    @Multipart
    @POST("/api/v1/product_images")
    suspend fun uploadProductImages(
        @Part images: MultipartBody.Part
    ): ApiResponse<ProductImageUploadResponse>

    @POST("/api/v1/products")
    fun registerProduct(
        @Body request: ProductRegistrationRequest
    ): ApiResponse<Response<Void>>

    companion object {
        val instance = ApiGenerator().generate(SandraMallApi::class.java)
    }
}