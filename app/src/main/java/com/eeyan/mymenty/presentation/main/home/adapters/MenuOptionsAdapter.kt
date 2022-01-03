package com.eeyan.mymenty.presentation.main.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.eeyan.mymenty.databinding.RowMenuOptionsBinding
import com.eeyan.mymenty.domain.model.HomeMenu
import com.eeyan.mymenty.presentation.main.home.HomeDirections

class MenuOptionsAdapter(private val optionsList:ArrayList<HomeMenu.Options>)
    : RecyclerView.Adapter<MenuOptionsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowMenuOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val optionsHome = optionsList[position]
        holder.bind(optionsHome)
        holder.initClickEvents(position)
    }

    override fun getItemCount(): Int = optionsList.size

    class ViewHolder(private val binding:RowMenuOptionsBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(options:HomeMenu.Options){
                binding.imgRowMenuOptions.load(options.img)
                binding.txtMenuName.text = options.title
            }

            fun initClickEvents(position: Int){
                binding.root.setOnClickListener {
                    when(position){
                        0 -> {
                            val myAction = HomeDirections.actionHomeToAppTimer(timerMode = 0)
                            navigate(it, myAction)
                        }
                        1 -> {

                        }
                        2 -> {
                            val myAction = HomeDirections.actionHomeToAppTimer(timerMode = 1)
                            navigate(it, myAction)
                        }
                        3 -> {}
                    }
                }

            }


            private fun navigate(mView:View, action:NavDirections){
                Navigation.findNavController(mView).navigate(action)
            }

    }

}