package com.sooyeon.sandramall.product.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.codephobia.ankomvvm.components.BaseFragment
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import java.lang.IllegalStateException

class ProductListFragment : BaseFragment<ProductListViewModel>() {
    override val viewModelType = ProductListViewModel::class

    val categoryId
        get() = arguments?.getInt("categoryId")
            ?: throw IllegalStateException("no category ID")

    val title
        get() = arguments?.getString("title")
            ?: throw IllegalStateException("no title")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return AnkoContext.create(ctx, this).verticalLayout {
            textView(categoryId.toString())
            textView(title)
        }
    }


    companion object {
        fun newInstance(categoryId: Int, title: String) =
            ProductListFragment().apply {
                arguments = Bundle().also {
                    it.putInt("categoryId", categoryId)
                    it.putString("title", title)
                }
            }
    }
}