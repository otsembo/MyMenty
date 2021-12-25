package com.eeyan.mymenty.domain.repository

import android.util.Log
import com.eeyan.mymenty.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository
    @Inject
    constructor(private val auth:FirebaseAuth,
                private val currentUser:FirebaseUser?,
                private val dbRef:DatabaseReference){

    private val TAG:String = "AuthRepository"

    //create account
        suspend fun createAccount(email:String, password:String) : Boolean{
            var isLoggedIn = false
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    isLoggedIn = it.isSuccessful
                }.await()
            return isLoggedIn
        }

        //get user id
        private fun getUserId() : String? {
            return currentUser?.uid
        }

        //log in
        suspend fun loginAccount(email: String, password: String) : Boolean{
            var isLoggedIn = false
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    isLoggedIn = it.isSuccessful
            }.await()
            return isLoggedIn
        }


        //save user data
        suspend fun saveUserData(email: String, username:String){
            //user data
            val user = User(getUserId()!!, email, imageUrl = null, username)
            //save to database
            dbRef.child(USERS)
                .child(getUserId()!!)
                .setValue(user)
                .addOnCompleteListener {  }.await()
        }

        //get user data
        @ExperimentalCoroutinesApi
        suspend fun getUserData()  = callbackFlow<User> {
                dbRef.child(USERS).child(getUserId()!!).addValueEventListener(
                    object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val user = snapshot.getValue(User::class.java)
                            if (user != null) {
                               this@callbackFlow.trySendBlocking(user).getOrThrow()
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                            this@callbackFlow.close(error.toException())
                        }
                    }
                )

                awaitClose {}

            }

        // log out user
        fun logoutUser(){
            auth.signOut()
        }




    companion object {
        //database reference
        private const val USERS = "users"
    }


}