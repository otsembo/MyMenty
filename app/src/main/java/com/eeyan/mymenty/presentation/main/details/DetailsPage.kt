package com.eeyan.mymenty.presentation.main.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eeyan.mymenty.common.constants.Constants
import com.eeyan.mymenty.databinding.MainHomeWebviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsPage : Fragment() {

    private lateinit var binding:MainHomeWebviewBinding

    private  val mCtx:FragmentActivity by lazy {
        requireActivity()
    }

    private val args:Bundle? by lazy {
        arguments
    }

    private val viewModel:DetailsPageVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainHomeWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url:String = args?.get(Constants.WEB_DETAILS) as String
        setUpWebView(url)

    }


    //set up web view
    private fun setUpWebView(url:String?){
        with(binding.webViewDetails){
            settings.javaScriptEnabled = true
            webChromeClient = DetailsPageVM.AppChromeClient()
            webViewClient = DetailsPageVM.AppWebClient(mCtx)
            loadUrl(url!!)
        }
    }



}