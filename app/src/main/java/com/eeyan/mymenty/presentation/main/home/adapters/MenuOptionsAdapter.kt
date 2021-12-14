package com.eeyan.mymenty.presentation.main.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.eeyan.mymenty.databinding.RowMenuOptionsBinding
import com.eeyan.mymenty.domain.model.HomeMenu

class MenuOptionsAdapter(private val optionsList:ArrayList<HomeMenu.Options>)
    : RecyclerView.Adapter<MenuOptionsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowMenuOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val optionsHome = optionsList[position]
        holder.bind(optionsHome)
    }

    override fun getItemCount(): Int = optionsList.size

    class ViewHolder(private val binding:RowMenuOptionsBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(options:HomeMenu.Options){
                binding.imgRowMenuOptions.load(options.img)
                binding.txtMenuName.text = options.title
            }

    }

}