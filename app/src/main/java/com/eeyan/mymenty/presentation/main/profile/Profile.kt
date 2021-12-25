package com.eeyan.mymenty.presentation.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eeyan.mymenty.R
import com.eeyan.mymenty.databinding.MainProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Profile : Fragment(){

    private lateinit var binding: MainProfileBinding

    private val viewModel:ProfileVM by viewModels()

    private val mCtx:FragmentActivity by lazy {
        requireActivity()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MainProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserDetails()

        initClicks()
        initObservers()

    }

    private fun initClicks(){
        binding.btnExit.setOnClickListener {
            viewModel.signOut()
            Navigation.findNavController(it).navigate(R.id.action_profile_to_authentication)
            mCtx.finish()
        }
    }

    private fun initObservers(){

        viewModel.profileState.observe(viewLifecycleOwner, {
            if(it?.data?.equals(null) != true){
                it?.data?.let { user ->
                    binding.txtProfileName.text = user.username
                    binding.txtProfileEmail.text = user.email
                }

            }
        })

    }

}