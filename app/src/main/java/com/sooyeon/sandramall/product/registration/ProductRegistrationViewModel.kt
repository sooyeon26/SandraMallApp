package com.sooyeon.sandramall.product.registration

import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sooyeon.sandramall.api.request.ProductRegistrationRequest
import com.sooyeon.sandramall.api.response.ApiResponse
import com.sooyeon.sandramall.product.category.categoryList
import com.sooyeon.sandramall.product.response.ProductImageUploadResponse
import kotlinx.coroutines.launch
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import retrofit2.Response

class ProductRegistrationViewModel(app: Application) : BaseViewModel(app) {

    val imageUrls: List<MutableLiveData<String?>> = listOf(
        MutableLiveData(null as String?),
        MutableLiveData(null as String?),
        MutableLiveData(null as String?),
        MutableLiveData(null as String?),
        MutableLiveData(null as String?)
    )

    val imageIds: MutableList<Long?> =
        mutableListOf(null, null, null, null)

    var currentImageNum: Int = 0

    val productName = MutableLiveData("")
    val description = MutableLiveData("")
    val price = MutableLiveData("")
    val categories = MutableLiveData(categoryList.map { it.name })
    var categoryIdSelected: Int? = categoryList[0].id

    val descriptionLimit = 500
    val productNameLimit = 40

    val productNameLength = MutableLiveData("0/$productNameLimit")
    val descriptionLength = MutableLiveData("0/$descriptionLimit")


    fun checkProductNameLength() {
        productName.value?.let {
            if (it.length > productNameLimit) {
                productName.value = it.take(productNameLimit)
            }
            productNameLength.value = "${productName.value?.length}/$productNameLimit"
        }
    }

    fun checkDescriptionLength() {
        description.value?.let {
            if (it.length > descriptionLimit) {
                description.value = it.take(descriptionLimit)
            }
            descriptionLength.value = "${description.value?.length}/$descriptionLimit"
        }
    }

    fun onCategorySelect(position: Int) {
        categoryIdSelected = categoryList[position].id
    }

    fun pickImage(imageNum: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }

        intent.resolveActivity(app.packageManager)?.let {
            startActivityForResult(intent, REQUEST_PICK_IMAGES)
        }

        currentImageNum = imageNum
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) {
            return
        }

        when (requestCode) {
            REQUEST_PICK_IMAGES -> data?.let { uploadImage(it) }
        }
    }

    private fun uploadImage(intent: Intent) =
        getContent(intent.data)?.let { imageFile ->
            viewModelScope.launch {
                val response = ProductImageUploader().upload(imageFile)
                onImageUploadResponse(response)
            }
        }

    private fun onImageUploadResponse(
        response: ApiResponse<ProductImageUploadResponse>
    ) {
        if (response.success && response.data != null) {
            imageUrls[currentImageNum].value = response.data.filePath
            imageIds[currentImageNum] = response.data.productImageId
        } else {
            toast(response.message ?: "unknown error")
        }
    }

    suspend fun register() {
        val request = extractRequest()
        val response = ProductRegistrar().register(request)
        onRegistrationResponse(response)
    }

    private fun extractRequest(): ProductRegistrationRequest =
        ProductRegistrationRequest(
            productName.value,
            description.value,
            price.value?.toIntOrNull(),
            categoryIdSelected,
            imageIds
        )

    private fun onRegistrationResponse(
        response: ApiResponse<Response<Void>>
    ) {
        if (response.success) {
            confirm("product registered successfully") {
                finishActivity()
            }
        } else {
            toast(response.message ?: "unknown error when registering product")
        }
    }

    companion object {
        const val REQUEST_PICK_IMAGES = 0
    }

}