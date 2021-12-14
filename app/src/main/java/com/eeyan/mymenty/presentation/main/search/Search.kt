package com.eeyan.mymenty.presentation.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.eeyan.mymenty.common.loader.LoadingUtil
import com.eeyan.mymenty.databinding.MainSearchBinding
import com.eeyan.mymenty.presentation.main.search.adapters.SearchTipsAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Search : Fragment(){

    private lateinit var binding: MainSearchBinding

    private val viewModel:SearchVM by viewModels()

    private lateinit var adapter: SearchTipsAdapter

    private lateinit var mCtx:FragmentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MainSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mCtx = requireActivity()

        initObservers()

        binding.imgSearch.setOnClickListener {
            binding.fragNothingFound.visibility = View.GONE
            viewModel.searchHelp(binding.edtSearchHelp.text.toString())
        }

    }

    private fun initObservers(){
        viewModel.tipsState.observe(viewLifecycleOwner, {

            val data = it.data
            val message = it.message
            val isLoading = it.isLoading

            if(isLoading)
                LoadingUtil.showDialog(mCtx, false)


            data?.let { mData ->

                LoadingUtil.hideDialog()

                adapter = SearchTipsAdapter(mData)
                binding.rvHelp.adapter = adapter

                if(mData.isEmpty())
                    binding.fragNothingFound.visibility = View.VISIBLE


            }



            message?.let { m ->
                displaySnack(m)
            }

        })
    }


    //display message
    private fun displaySnack(message:String){
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

}