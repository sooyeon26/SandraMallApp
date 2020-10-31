package com.sooyeon.sandramall.product.list

import android.app.Application
import android.content.Intent
import androidx.paging.DataSource
import com.sooyeon.sandramall.api.response.ProductListItemResponse
import com.sooyeon.sandramall.product.detail.ProductDetailActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.lang.IllegalStateException

class ProductListViewModel(
    app: Application
) : BaseViewModel(app),
    ProductListPagedAdapter.ProductLiveDataBuilder,
    ProductListPagedAdapter.OnItemClickListener {

    var categoryId: Int = -1
    val products = buildPagedList()

    fun onClickItem(id: Long?) {
        toast("click $id")
    }

    override fun createDataSource(): DataSource<Long, ProductListItemResponse> {
        if (categoryId == -1)
            error(
                "invalid category ID",
                IllegalStateException("category Id is -1")
            )
        return ProductListItemDataSource(categoryId)
    }

    override fun onClickProduct(productId: Long?) {
        onClickItem(productId)

        startActivity<ProductDetailActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(ProductDetailActivity.PRODUCT_ID, productId)
        }
    }
}