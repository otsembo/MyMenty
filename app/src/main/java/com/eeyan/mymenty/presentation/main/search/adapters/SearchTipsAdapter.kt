package com.eeyan.mymenty.presentation.main.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.eeyan.mymenty.R
import com.eeyan.mymenty.databinding.RowSearchBinding
import com.eeyan.mymenty.domain.model.HealthTip

class SearchTipsAdapter (private val healthTips: List<HealthTip>) : RecyclerView.Adapter<SearchTipsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(healthTips[position])
    }

    override fun getItemCount(): Int = healthTips.size

    class ViewHolder(private val binding: RowSearchBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindItems(tip: HealthTip){
            binding.txtSearchTip.text = tip.title
            binding.imgTip.load(tip.image){
                placeholder(R.drawable.ic_tip_placeholder)
            }
        }

    }

}