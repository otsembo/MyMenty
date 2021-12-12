package com.eeyan.mymenty.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eeyan.mymenty.R
import com.eeyan.mymenty.databinding.MainHomeBinding
import com.eeyan.mymenty.di.AppModule
import com.eeyan.mymenty.domain.model.HealthTip
import com.eeyan.mymenty.presentation.main.home.adapters.HealthTipAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment() {

    private lateinit var binding: MainHomeBinding

    private val viewModel:HomeVM by viewModels()

    private lateinit var healthTipAdapter:HealthTipAdapter

    private lateinit var mCtx:FragmentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MainHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mCtx = requireActivity()

        if(AppModule.getFirebaseAuth().currentUser == null){
            Navigation.findNavController(view).navigate(R.id.action_home_to_authentication)
            mCtx.finish()
        }

        initObservers()
    }

    //init observers
    private fun initObservers(){
        viewModel.tipsState.observe(viewLifecycleOwner, {

            if (!it.data.isNullOrEmpty()){
                binding.tipsLoader.visibility = View.GONE
                healthTipAdapter = HealthTipAdapter( healthTips = it.data!! )
                binding.rvTipsDaily.adapter = healthTipAdapter
            }

        })
    }

}