package com.eeyan.mymenty.presentation.main.profile.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.eeyan.mymenty.R
import com.eeyan.mymenty.databinding.RowProfileOptionsBinding
import com.eeyan.mymenty.domain.model.ProfileMenu
import com.eeyan.mymenty.presentation.main.profile.ProfileVM

class ProfileMenuAdapter
    (private val profileOptions:ArrayList<ProfileMenu.ProfileOptions>) : RecyclerView.Adapter<ProfileMenuAdapter.ProfileVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileVH {
        return ProfileVH(
            RowProfileOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileVH, position: Int) {
        val option = profileOptions[position]
        holder.bind(option)
    }

    override fun getItemCount(): Int = profileOptions.size

    class ProfileVH (val binding: RowProfileOptionsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(option:ProfileMenu.ProfileOptions){
            binding.imgProfileMenu.load(
                option.icon
            )
            binding.txtProfileMenuTitle.text = option.title
        }
    }
}