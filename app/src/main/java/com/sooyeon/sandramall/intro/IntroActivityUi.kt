package com.sooyeon.sandramall.intro

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import com.sooyeon.sandramall.R
import org.jetbrains.anko.*

class IntroActivityUi : AnkoComponent<IntroActivity> {
    override fun createView(ui: AnkoContext<IntroActivity>): View {

        return ui.relativeLayout {
            gravity = Gravity.CENTER

            textView("SandraMall") {
                textSize = 24f
                textColorResource = R.color.colorPrimary
                typeface = Typeface.DEFAULT_BOLD
            }
        }
    }

}