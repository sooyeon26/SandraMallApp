package com.sooyeon.sandramall.signin

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sooyeon.sandramall.Prefs
import com.sooyeon.sandramall.api.SandraMallApi
import com.sooyeon.sandramall.api.request.SigninRequest
import com.sooyeon.sandramall.api.response.ApiResponse
import com.sooyeon.sandramall.api.response.SigninResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.lang.Exception

class SigninViewModel(app: Application) : BaseViewModel(app) {

    val email = MutableLiveData("")
    val password = MutableLiveData("")


    suspend fun signin() {
        val request = SigninRequest(email.value, password.value)
        if (isNotValidSignin(request))
            return

        try {
            val response = requestSignin(request)
            onSigninResponse(response)
        } catch (e: Exception) {
            error("signin error", e)
            toast("unknown error")
        }
    }

    private fun onSigninResponse(response: ApiResponse<SigninResponse>) {

        if (response.success && response.data != null) {

            Prefs.token = response.data.token
            Prefs.refreshToken = response.data.refreshToken
            Prefs.userName = response.data.userName
            Prefs.userId = response.data.userId

            toast("Successfully logged in")
            //TODO move to the item list page
        } else {
            toast(response.message ?: "unknown error")
        }
    }

    private suspend fun requestSignin(signinRequest: SigninRequest) =
        withContext(Dispatchers.IO) {
            SandraMallApi.instance.signin(signinRequest)
        }

    private fun isNotValidSignin(signinRequest: SigninRequest) =
        when {
            signinRequest.isNotValidEmail() -> {
                toast("email format is invalid")
                true
            }
            signinRequest.isNotValidPassword() -> {
                toast("password needs to be between 8 and 20 characters")
                true
            }
            else -> false
        }
}