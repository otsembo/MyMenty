package com.eeyan.mymenty.presentation.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eeyan.mymenty.R
import com.eeyan.mymenty.common.loader.LoadingUtil
import com.eeyan.mymenty.databinding.AuthLoginBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : Fragment() {

    private lateinit var binding: AuthLoginBinding

    private val viewModel:LoginVM by viewModels()

    //context
    private lateinit var mCtx: FragmentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AuthLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCtx = requireActivity()

        initObservers()

        binding.txtGoToRegister.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_login_to_register)
        }

        binding.btnLogin.setOnClickListener {
            LoadingUtil.showDialog(mCtx, false)
            login()
        }
    }


    //init observers
    private fun initObservers(){
        viewModel.loginState.observe(viewLifecycleOwner) {
            it.message?.let { info ->
                Snackbar.make(binding.root, info, Snackbar.LENGTH_LONG).show()
            }

            if (!it.isLoading) LoadingUtil.hideDialog()

            it.data?.let { isRegistered ->
                if (isRegistered) {
                    navigateToHome()
                } else
                    navigateToHome()
            }
        }
    }

    //display message
    private fun displaySnack(message:String){
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    //login user
    private fun login(){
        val email = binding.edtLoginEmail.text.toString()
        val pass = binding.edtLoginPassword.text.toString()

        viewModel.loginUser(email, pass)
    }

    private fun navigateToHome(){
        displaySnack("Successfully logged in")

        Navigation.findNavController(binding.root)
            .navigate(R.id.action_login_to_mainActivity)
        mCtx.finish()
    }

}



























