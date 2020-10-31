package com.sooyeon.sandramall.product.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sooyeon.sandramall.api.SandraMallApi
import com.sooyeon.sandramall.api.response.ApiResponse
import com.sooyeon.sandramall.api.response.ProductResponse
import com.sooyeon.sandramall.product.ProductStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.codephobia.ankomvvm.databinding.addAll
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.lang.Exception
import java.text.NumberFormat

class ProductDetailViewModel(app: Application) : BaseViewModel(app) {

    var productId: Long? = null

    val productName = MutableLiveData("-")
    val description = MutableLiveData("")
    val price = MutableLiveData("-")
    val imageUrls: MutableLiveData<MutableList<String>> =
        MutableLiveData(mutableListOf())

    fun loadDetail(id: Long) = viewModelScope.launch(Dispatchers.Main) {
        try {
            val response = getProduct(id)
            if (response.success && response.data != null) {
                updateViewData(response.data)
            } else {
                toast(response.message ?: "unknown error while fetching product info")
            }
        } catch (e: Exception) {
            toast(e.message ?: "unknown error while fetching product info")
        }

    }

    private suspend fun getProduct(id: Long) = try {
        SandraMallApi.instance.getProduct(id)
    } catch (e: Exception) {
        error("error while fetching product info", e)
        ApiResponse.error<ProductResponse>(
            "error while fetching product info"
        )
    }

    private fun updateViewData(product: ProductResponse) {
        val commaSeparatedPrice =
            NumberFormat.getInstance().format(product.price)
        val soldOutString =
            if (ProductStatus.SOLD_OUT == product.status) "(SOLD OUT)" else ""

        productName.value = product.name
        description.value = product.description
        price.value =
            "$${commaSeparatedPrice} $soldOutString"
        imageUrls.addAll(product.imagePaths)
    }

    fun openInquiryActivity() {
        toast("ask question - productId = $productId")
    }

}