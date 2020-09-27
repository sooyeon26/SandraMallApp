package com.sooyeon.sandramall.product

import android.view.Gravity
import android.view.Menu.NONE
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.sooyeon.sandramall.Prefs
import com.sooyeon.sandramall.R
import com.sooyeon.sandramall.signin.SigninActivity
import com.sooyeon.sandramall.view.borderBottom
import org.jetbrains.anko.*
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.appcompat.v7.toolbar

class ProductMainUi(
    private val viewModel: ProductMainViewModel
) : AnkoComponent<ProductMainActivity>, NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var navigationView: NavigationView

    override fun createView(ui: AnkoContext<ProductMainActivity>) =
        ui.drawerLayout {
            drawerLayout = this

            verticalLayout {
                toolbar = toolbar {
                    title = "SandraMall"
                    bottomPadding = dip(1)
                    background = borderBottom(width = dip(1))
                    menu.add("Search")
                        .setIcon(R.drawable.ic_search)
                        .setShowAsAction(SHOW_AS_ACTION_ALWAYS)
                }.lparams(matchParent, wrapContent)
            }

            navigationView = navigationView {
                ProductMainNavHeader()
                    .createView(AnkoContext.create(context, this))
                    .let(::addHeaderView)
                menu.apply {
                    add(MENU_GROUP_ID_MY_INFO, MENU_ID_INQUIRY, NONE, "My Questions").apply {
                        setIcon(R.drawable.ic_question_gray)
                    }
                    add(MENU_GROUP_ID_MY_INFO, MENU_ID_SIGN_OUT, NONE, "Sign Out").apply {
                        setIcon(R.drawable.ic_signout_gray)
                    }
                }
                setNavigationItemSelectedListener(this@ProductMainUi)
            }.lparams(wrapContent, matchParent) {
                gravity = Gravity.START
            }
        }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            MENU_ID_INQUIRY -> {
                viewModel.toast("my questions TBD")
            }
            MENU_ID_SIGN_OUT -> {
                Prefs.token = null
                Prefs.refreshToken = null
                viewModel.startActivityAndFinish<SigninActivity>()
            }
        }
        drawerLayout.closeDrawer(navigationView)
        return true
    }

    companion object {
        private const val MENU_ID_INQUIRY = 1
        private const val MENU_ID_SIGN_OUT = 2
        private const val MENU_GROUP_ID_MY_INFO = 0
    }


}