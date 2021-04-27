package com.goutam.moviebank.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val _emailError = MutableLiveData<String>()
    val emailError: LiveData<String> = _emailError
    private val _passwordError = MutableLiveData<String>()
    val passwordError: LiveData<String> = _passwordError
    private val _isFormValidated = MutableLiveData<Boolean>(false)
    val isFormValidated: LiveData<Boolean> = _isFormValidated

    fun validateForm(email: String, password: String) {
        _isFormValidated.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email: String): Boolean{
        return if(email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
             _emailError.value = ""
            true
        } else {
            _emailError.value = "Please enter a valid email id."
            false
        }
    }
    private fun isValidPassword(password: String): Boolean{
        return if(password.isNotEmpty() && password.length >= 6 && password.length <= 12){
            _passwordError.value = ""
            true
        }else{
            _passwordError.value = "Please enter password between between 6 - 12 characters"
            false
        }
    }

}
