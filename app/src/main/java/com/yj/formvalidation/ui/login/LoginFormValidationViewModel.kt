package com.yj.formvalidation.ui.login

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yj.formvalidation.utils.Constants
import com.yj.formvalidation.utils.Helper

class LoginFormValidationViewModel : ViewModel() {

    val emailAddress = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val emailErrorMessage = MutableLiveData<String>()
    val passwordErrorMessage = MutableLiveData<String>()

    val isEmailValid = MediatorLiveData<Boolean>().apply {
        addSource(emailAddress) { value = Helper.isValidEmail(it) }
    }

    val isPasswordValid = MediatorLiveData<Boolean>().apply {
        addSource(password) { value = Helper.isValidPassword(it) }
    }

    val isFormValid = MediatorLiveData<Boolean>().apply {
        addSource(isEmailValid) {
            emailErrorMessage.value = if (it == true) null else "Invalid email"
            value = isEmailValid.value == true && isPasswordValid.value == true
            Log.d(Constants.TAG, "${emailErrorMessage.value} ")
        }
        addSource(isPasswordValid) {
            passwordErrorMessage.value = if (it == true) null else "Invalid password"
            value = isEmailValid.value == true && isPasswordValid.value == true
            Log.d(Constants.TAG, "${passwordErrorMessage.value} ")
        }
    }

}