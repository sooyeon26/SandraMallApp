package com.sooyeon.sandramall.product

import android.app.Application
import com.sooyeon.sandramall.product.registration.ProductRegistrationActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class ProductMainViewModel(app: Application) : BaseViewModel(app) {
    fun openProductRegistrationActivity() {
        startActivity<ProductRegistrationActivity>()
    }
}