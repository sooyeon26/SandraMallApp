package com.sooyeon.sandramall.product

import android.app.Application
import android.content.Intent
import com.sooyeon.sandramall.product.registration.ProductRegistrationActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductMainViewModel(app: Application) : BaseViewModel(app) {
    fun openProductRegistrationActivity() {
        startActivity<ProductRegistrationActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
    }
}