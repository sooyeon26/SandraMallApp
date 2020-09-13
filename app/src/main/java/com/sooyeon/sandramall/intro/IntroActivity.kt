package com.sooyeon.sandramall.intro

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.sooyeon.sandramall.api.SandraMallApi
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.setContentView

class IntroActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = IntroActivityUi()
        ui.setContentView(this)

        runBlocking {
            try {
                val response = SandraMallApi.instance.hello()
                Log.d(TAG, response.data)
            } catch (e: Exception) {
                Log.e(TAG, "HelloApi error", e)
            }
        }
    }


    companion object {
        val TAG = IntroActivity.javaClass.simpleName
    }
}