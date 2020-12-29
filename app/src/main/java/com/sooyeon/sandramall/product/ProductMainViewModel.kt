package com.sooyeon.sandramall.product

import android.app.Application
import android.content.Intent
import com.sooyeon.sandramall.product.registration.ProductRegistrationActivity
import com.sooyeon.sandramall.product.search.ProductSearchActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductMainViewModel(app: Application) : BaseViewModel(app) {
    fun openProductRegistrationActivity() {
        startActivity<ProductRegistrationActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
    }

    fun openSearchActivity(keyword: String?) {
        keyword?.let {
            startActivity<ProductSearchActivity> {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                putExtra(ProductSearchActivity.KEYWORD, keyword)
            }
        } ?: toast("please enter a keyword")
    }
}