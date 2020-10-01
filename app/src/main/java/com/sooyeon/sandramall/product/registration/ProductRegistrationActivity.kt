package com.sooyeon.sandramall.product.registration

import android.os.Bundle
import android.view.MenuItem
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class ProductRegistrationActivity : BaseActivity<ProductRegistrationViewModel>() {
    override val viewModelType = ProductRegistrationViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ProductRegistrationUi(getViewModel())
            .setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Upload Product"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (item.itemId) {
                android.R.id.home -> onBackPressed()
                else -> {
                }
            }
        }
        return true
    }
}