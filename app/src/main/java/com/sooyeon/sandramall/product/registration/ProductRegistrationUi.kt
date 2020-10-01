package com.sooyeon.sandramall.product.registration

import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.scrollView
import org.jetbrains.anko.verticalLayout

class ProductRegistrationUi(private val viewModel: ProductRegistrationViewModel) :
    AnkoComponent<ProductRegistrationActivity> {
    override fun createView(ui: AnkoContext<ProductRegistrationActivity>) =
        ui.scrollView {
            verticalLayout { }
        }

}