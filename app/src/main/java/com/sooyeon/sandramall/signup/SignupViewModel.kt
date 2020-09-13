package com.sooyeon.sandramall.signup

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sooyeon.sandramall.api.ApiResponse
import com.sooyeon.sandramall.api.SandraMallApi
import com.sooyeon.sandramall.api.request.SignupRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.lang.Exception

class SignupViewModel(app: Application) : BaseViewModel(app) {
    val email = MutableLiveData("")
    val name = MutableLiveData("")
    val password = MutableLiveData("")

    suspend fun signup() {
        val request = SignupRequest(email.value, name.value, password.value)
        if (isNotValidSignUp(request)) {
            return
        }

        try {
            val response = requestSignup(request)
            onSignupResponse(response)
        } catch (e: Exception) {
            error("signup error", e)
            toast("unknown error")
        }
    }

    private fun onSignupResponse(response: ApiResponse<Void>) {

        if (response.success) {
            toast("registered your account successfully. please log in.")
            finishActivity()
        } else {
            toast(response.message ?: "failed to register your account")
        }
    }

    private suspend fun requestSignup(signupRequest: SignupRequest) =
        withContext(Dispatchers.IO) {
            SandraMallApi.instance.signup(signupRequest)
        }


    private fun isNotValidSignUp(signupRequest: SignupRequest) =
        when {
            signupRequest.isNotValidEmail() -> {
                toast("your email format is incorrect")
                true
            }

            signupRequest.isNotValidName() -> {
                toast("your name has to be between 8 to 20 characters")
                true
            }

            signupRequest.isNotValidPassword() -> {
                toast("your password has to be between 2 to 20 characters")
                true
            }
            else -> false
        }
}
