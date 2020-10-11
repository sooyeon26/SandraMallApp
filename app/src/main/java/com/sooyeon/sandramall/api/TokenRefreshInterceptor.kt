package com.sooyeon.sandramall.api

import android.content.Intent
import com.sooyeon.sandramall.App
import com.sooyeon.sandramall.Prefs
import com.sooyeon.sandramall.signin.SigninActivity
import okhttp3.Interceptor
import okhttp3.Response
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.intentFor

class TokenRefreshInterceptor : Interceptor, AnkoLogger {
    override fun intercept(chain: Interceptor.Chain): Response {
        debug("Refresh token requested")
        val original = chain.request()
        val request = original.newBuilder().apply {
            Prefs.refreshToken?.let { header("Authorization", it) }
            method(original.method(), original.body())
        }.build()

        val response = chain.proceed(request)

        if (response.code() == 401) {
            App.instance.run {
                val intent = intentFor<SigninActivity>().apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
            }
        }

        return response

    }

}