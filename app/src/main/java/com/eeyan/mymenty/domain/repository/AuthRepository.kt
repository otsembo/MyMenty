package com.eeyan.mymenty.domain.repository

import com.eeyan.mymenty.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository
    @Inject
    constructor(private val auth:FirebaseAuth,
                private val currentUser:FirebaseUser?,
                private val dbRef:DatabaseReference){

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
        fun getUserId() : String? {
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
        fun getUserData() : User {
            var userX = User("0","0","0", "0")
            dbRef.child(USERS).child(getUserId()!!).addValueEventListener(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val user = snapshot.value as User
                        userX = user
                    }
                    override fun onCancelled(error: DatabaseError) {}
                }
            )

            return userX;
        }

    companion object {
        //database reference
        private const val USERS = "users"
    }


}