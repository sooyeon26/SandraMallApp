package com.sooyeon.sandramall.intro

import android.app.Activity
import android.os.Bundle
import com.sooyeon.sandramall.signup.SignupActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class IntroActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IntroActivityUi().setContentView(this)

        GlobalScope.launch {
            delay(1000)
            startActivity<SignupActivity>()
            finish()
        }
    }


    companion object {
        val TAG = IntroActivity.javaClass.simpleName
    }
}