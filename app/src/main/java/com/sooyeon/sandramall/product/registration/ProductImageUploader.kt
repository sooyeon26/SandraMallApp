package com.sooyeon.sandramall.product.registration

import com.sooyeon.sandramall.api.SandraMallApi
import com.sooyeon.sandramall.api.response.ApiResponse
import com.sooyeon.sandramall.product.response.ProductImageUploadResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import java.io.File

class ProductImageUploader : AnkoLogger {
    suspend fun upload(imageFile: File) = try {
        val part = makeImagePart(imageFile)
        withContext(Dispatchers.IO) {
            SandraMallApi.instance.uploadProductImages(part)
        }
    } catch (e: Exception) {
        error("error uploading image", e)
        ApiResponse.error<ProductImageUploadResponse>(
            "unknown error while uploading image"
        )
    }

    private fun makeImagePart(imageFile: File): MultipartBody.Part {
        val mediaType = MediaType.parse("multipart/form-data")
        val body = RequestBody.create(mediaType, imageFile)

        return MultipartBody.Part
            .createFormData("image", imageFile.name, body)
    }
}