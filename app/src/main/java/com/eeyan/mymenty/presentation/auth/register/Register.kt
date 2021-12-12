package com.eeyan.mymenty.presentation.auth.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eeyan.mymenty.R
import com.eeyan.mymenty.common.loader.LoadingUtil
import com.eeyan.mymenty.databinding.AuthRegisterBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Register : Fragment() {

    //binding object
    private lateinit var binding: AuthRegisterBinding

    //view model
    private val viewModel:RegisterVM by viewModels()

    //context
    private lateinit var mCtx:FragmentActivity

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AuthRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCtx = requireActivity()

        initObservers()
        binding.btnRegister.setOnClickListener { registerUser() }

        binding.txtGoToLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_register_to_login)
        }

    }


    //init observers
    private fun initObservers(){
        viewModel.registrationState.observe(viewLifecycleOwner, {

            it.message?.let { info ->
                Snackbar.make(binding.root, info, Snackbar.LENGTH_LONG).show()
            }

            if(!it.isLoading) LoadingUtil.hideDialog()

            it.data?.let { isRegistered ->
                if(isRegistered) {
                    displaySnack("Successfully created account")
                    saveUserData()

                    Navigation.findNavController(binding.root).navigate(R.id.action_register_to_mainActivity)
                    mCtx.finish()

                }
                else
                    displaySnack(it.message!!)
            }

        })
    }

    //register user
    private fun registerUser(){

        val email = binding.edtEmail.text.toString()
        val pass = binding.edtPassword.text.toString()

        if(isUserDataValid()) {
            viewModel.registerUser(email, pass)
            LoadingUtil.showDialog(requireActivity(),false)
        }




    }

    //save user data
    private fun saveUserData(){

        val email = binding.edtEmail.text.toString()
        val username = binding.edtUsername.text.toString()

        viewModel.saveUserData(email, username)

    }

    //display message
    private fun displaySnack(message:String){
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    //validate registration data
    private fun isUserDataValid() : Boolean{

        when {
            binding.edtUsername.text.toString().length < 5 ->
            {
                displaySnack("Username should be at least 5 characters")
                return false
            }
            binding.edtPassword.text.toString().length < 6 -> {
                displaySnack("Password should be at least 6 characters")
                return false
            }
            binding.edtPassword.text.toString() != binding.edtRepeatPass.text.toString() -> {
                displaySnack("Passwords do not match")
                return false
            }

            !Patterns.EMAIL_ADDRESS.matcher(binding.edtEmail.text.toString()).matches() -> {
                displaySnack("You need a valid email")
                return false
            }

            else -> {
                return true
            }
        }


    }


}










