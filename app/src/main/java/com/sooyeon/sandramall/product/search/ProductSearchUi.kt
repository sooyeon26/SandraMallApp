package com.sooyeon.sandramall.product.search

import android.util.Log
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sooyeon.sandramall.product.list.ProductListPagedAdapter
import net.codephobia.ankomvvm.databinding.bindPagedList
import net.codephobia.ankomvvm.databinding.bindVisibility
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ProductSearchUi(
    private val viewModel: ProductSearchViewModel
) : AnkoComponent<ProductSearchActivity> {
    override fun createView(ui: AnkoContext<ProductSearchActivity>) =
        ui.verticalLayout {
            recyclerView {
                layoutManager = LinearLayoutManager(ui.ctx)
                adapter = ProductListPagedAdapter(viewModel)
                lparams(matchParent, matchParent)
                bindVisibility(ui.owner, viewModel.products) {
                    it.isNotEmpty()
                }
                bindPagedList(
                    ui.owner,
                    ProductListPagedAdapter(viewModel),
                    viewModel.products
                )
            }

            textView("there are no matching products. try a different keyword :)") {
                Log.d("ProductSearchUi", "there are no products")
                gravity = Gravity.CENTER
                bindVisibility(ui.owner, viewModel.products) {
                    it.isEmpty()
                }
            }.lparams(wrapContent, matchParent) {
                gravity = Gravity.CENTER
            }
        }
}