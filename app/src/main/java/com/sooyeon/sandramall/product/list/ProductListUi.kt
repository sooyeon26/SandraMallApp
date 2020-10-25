package com.sooyeon.sandramall.product.list

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import net.codephobia.ankomvvm.databinding.bindPagedList
import net.codephobia.ankomvvm.databinding.bindVisibility
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ProductListUi(
    private val viewModel: ProductListViewModel
) : AnkoComponent<ProductListFragment> {
    override fun createView(ui: AnkoContext<ProductListFragment>) =
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

            textView("there are no products") {
                gravity = Gravity.CENTER
                bindVisibility(ui.owner, viewModel.products) {
                    it.isNotEmpty()
                }
            }.lparams(wrapContent, matchParent) {
                gravity = Gravity.CENTER
            }
        }
}