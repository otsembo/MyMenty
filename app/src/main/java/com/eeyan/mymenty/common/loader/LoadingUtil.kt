package com.eeyan.mymenty.common.loader

import android.content.Context
import android.widget.Toast
import java.lang.Exception

open class LoadingUtil {

    companion object{

        private var loader : AnimatorDialog? = null

        fun showDialog(mCtx:Context, isCancelable:Boolean){

            hideDialog()

            try{

                loader = AnimatorDialog(mCtx)

                with(loader!!){
                    setCancelable(isCancelable)
                    setCanceledOnTouchOutside(isCancelable)
                    show()
                }

            }catch (e:Exception){

                Toast.makeText(mCtx, "An unexpected error occurred. Restart the application", Toast.LENGTH_SHORT).show()

            }

        }


        fun hideDialog(){

            if (loader != null && loader?.isShowing!!){

                loader = try {

                    loader?.dismiss()
                    null
                }catch (e: Exception){

                    null
                }

            }

        }

    }

}