package com.eeyan.mymenty.common.loader

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import com.eeyan.mymenty.R
import com.eeyan.mymenty.databinding.DialogAnimatorBinding

class AnimatorDialog (mCtx: Context) :  Dialog(mCtx){

    //binding object
    private lateinit var binding:DialogAnimatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAnimatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.let{

            it.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )

            it.setBackgroundDrawableResource(R.color.transparent_50)
        }

    }

}