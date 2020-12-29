package com.sooyeon.sandramall.product.search

import android.app.Application
import android.content.Intent
import com.sooyeon.sandramall.product.detail.ProductDetailActivity
import com.sooyeon.sandramall.product.list.ProductListItemDataSource
import com.sooyeon.sandramall.product.list.ProductListPagedAdapter
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductSearchViewModel(
    app: Application
) : BaseViewModel(app),
    ProductListPagedAdapter.ProductLiveDataBuilder,
    ProductListPagedAdapter.OnItemClickListener {

    var keyword: String? = null
    val products = buildPagedList()

    override fun createDataSource() =
        ProductListItemDataSource(null, keyword)

    override fun onClickProduct(productId: Long?) {
        startActivity<ProductDetailActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(ProductDetailActivity.PRODUCT_ID, productId)
        }
    }
}