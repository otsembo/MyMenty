package com.eeyan.mymenty.presentation.main.timer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgsLazy
import androidx.navigation.fragment.navArgs
import com.eeyan.mymenty.R
import com.eeyan.mymenty.common.constants.Constants
import com.eeyan.mymenty.databinding.DialogTimerBinding

class TimerDialog : DialogFragment() {

    private lateinit var binding:DialogTimerBinding
    private val viewModel:AppTimerVM by viewModels(ownerProducer = {requireParentFragment()})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TimerDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DialogTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar()
    }

    override fun onStart() {
        super.onStart()
        expandWindow()

    }


    private fun onTimeSelected(){
        with(binding.tp){
            viewModel.setTimer(currentHour, currentMinute)
        }

    }

    private fun expandWindow(){
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }


    private fun initToolBar(){
        with(binding.timerDialogToolbar){
            setNavigationOnClickListener { dismiss() }
            title = Constants.TIMER_DIALOG_TITLE
            inflateMenu(R.menu.timer_dialog_menu)
            setOnMenuItemClickListener {
                onTimeSelected()
                dismiss()
                true
            }
        }
    }

    companion object {

        const val TAG = "TimerDialog"

        fun displayDialog(fm:FragmentManager) : TimerDialog{
            val dialog = TimerDialog()
            dialog.show(fm, TAG)
            return dialog
        }

    }

}