package com.sooyeon.sandramall.product.list

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.sooyeon.sandramall.product.category.categoryList

class ProductListPageradapter(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private val fragments = categoryList.map {
        ProductListFragment.newInstance(it.id, it.name)
    }

    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
    override fun getPageTitle(position: Int) = getItem(position).title
}