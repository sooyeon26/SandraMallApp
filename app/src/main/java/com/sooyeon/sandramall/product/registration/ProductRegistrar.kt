package com.sooyeon.sandramall.product.registration

import com.sooyeon.sandramall.api.SandraMallApi
import com.sooyeon.sandramall.api.request.ProductRegistrationRequest
import com.sooyeon.sandramall.api.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import retrofit2.Response

class ProductRegistrar : AnkoLogger {
    suspend fun register(request: ProductRegistrationRequest) = when {
        request.isNotValidName ->
            ApiResponse.error("it's not a valid name")
        request.isNotValidDescription ->
            ApiResponse.error("it's not a valid description")
        request.isNotValidPrice ->
            ApiResponse.error("please retry price")
        request.isNotValidCategoryId ->
            ApiResponse.error("please select category")
        request.isNotValidImageIds ->
            ApiResponse.error("please upload at least one image")
        else -> withContext(Dispatchers.IO) {
            try {
                SandraMallApi.instance.registerProduct(request)
            } catch (e: Exception) {
                error("error registering product", e)
                ApiResponse.error<Response<Void>>("unknown error")
            }
        }


    }
}