package com.goutam.moviebank.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.goutam.moviebank.R
import com.goutam.moviebank.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var loginBinding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loginBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return loginBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginBinding.apply {
            etEmail.doAfterTextChanged {
                viewModel.validateForm(it.toString(), etPassword.text.toString())
            }

            etPassword.doAfterTextChanged {
                viewModel.validateForm(etEmail.text.toString(), it.toString())
            }

            btnSubmit.setOnClickListener {
                hideKeyBoard()
                findNavController().navigate(R.id.action_loginFragment_to_movieListFragment)
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            emailError.observe(viewLifecycleOwner, Observer {
                loginBinding.tilEmail.error = it
            })

            passwordError.observe(viewLifecycleOwner, Observer {
                loginBinding.tilPassword.error = it
            })

            isFormValidated.observe(viewLifecycleOwner, Observer {
                loginBinding.btnSubmit.isEnabled = it
            })
        }
    }

    private fun hideKeyBoard(){
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        requireActivity().currentFocus?.let {
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}
