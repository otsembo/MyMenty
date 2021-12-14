package com.eeyan.mymenty.di

import com.eeyan.mymenty.common.constants.Constants
import com.eeyan.mymenty.data.remote.HealthGovApi
import com.eeyan.mymenty.data.repository.HealthRepository
import com.eeyan.mymenty.domain.repository.AuthRepository
import com.eeyan.mymenty.domain.repository.HealthRepositoryImpl
import com.eeyan.mymenty.domain.use_case.home.MenuOptionsUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getFirebaseAuth() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun getFirebaseUser(mAuth: FirebaseAuth) : FirebaseUser?{
        return mAuth.currentUser
    }

    @Provides
    @Singleton
    fun getDbReference() : DatabaseReference{
        return FirebaseDatabase.getInstance().reference
    }


    @Provides
    @Singleton
    fun getHealthGovApi() : HealthGovApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HealthGovApi::class.java)
    }

    @Provides
    @Singleton
    fun getHealthRepository(api: HealthGovApi) : HealthRepository{
        return HealthRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun getOptionsMenuUseCase() : MenuOptionsUseCase{
        return MenuOptionsUseCase()
    }

}