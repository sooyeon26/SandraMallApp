package com.sooyeon.sandramall.api

import com.sooyeon.sandramall.Prefs
import com.sooyeon.sandramall.api.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

class TokenAuthenticator : Authenticator, AnkoLogger {
    override fun authenticate(route: Route?, response: Response): Request? {

        if (response.code() == 401) {
            debug("need to refresh token")
            return runBlocking {
                val tokenResponse = refreshToken()
                if (tokenResponse.success) {
                    debug("successfully refreshed token")
                    Prefs.token = tokenResponse.data
                } else {
                    error("failed to refresh token")
                    Prefs.token = null
                    Prefs.refreshToken = null
                }

                Prefs.token?.let { token ->
                    debug("token = $token")
                    response.request()
                        .newBuilder()
                        .header("Authorization", token)
                        .build()
                }
            }
        }

        return null

    }

    private suspend fun refreshToken() =
        withContext(Dispatchers.IO) {
            try {
                SandraMallRefreshApi.instance.refreshToken()
            } catch (e: Exception) {
                ApiResponse.error<String>("failed to authenticate user")
            }
        }

}