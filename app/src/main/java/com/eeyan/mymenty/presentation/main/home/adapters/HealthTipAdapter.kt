package com.eeyan.mymenty.presentation.main.home.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.eeyan.mymenty.R
import com.eeyan.mymenty.common.constants.Constants
import com.eeyan.mymenty.databinding.RowDailyTipsBinding
import com.eeyan.mymenty.domain.model.HealthTip

class HealthTipAdapter(private val healthTips: List<HealthTip>) : RecyclerView.Adapter<HealthTipAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowDailyTipsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(healthTips[position])
    }

    override fun getItemCount(): Int = healthTips.size

    class ViewHolder(private val binding: RowDailyTipsBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItems(tip: HealthTip){

            binding.txtTipName.text = tip.title
            binding.imgRowDailyTip.load(tip.image){
                placeholder(R.drawable.ic_tip_placeholder)
            }

            binding.root.setOnClickListener {
                val dataBundle = Bundle()
                dataBundle.putString(Constants.WEB_DETAILS, tip.url)
                Navigation.findNavController(it).navigate(R.id.action_home_to_detailsPage, dataBundle)
            }
        }

    }


}