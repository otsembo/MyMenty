package com.eeyan.mymenty.di

import android.content.Context
import androidx.room.Room
import com.eeyan.mymenty.common.constants.Constants
import com.eeyan.mymenty.data.local.dao.HealthTipDao
import com.eeyan.mymenty.data.local.database.MyMentyDatabase
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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun getFirebaseAuth() : FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun getFirebaseUser(mAuth: FirebaseAuth) : FirebaseUser?{
        return mAuth.currentUser
    }

    @Provides
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
    fun getHealthRepository(api: HealthGovApi, db:MyMentyDatabase, dao: HealthTipDao) : HealthRepository{
        return HealthRepositoryImpl(api, db, dao)
    }


    @Provides
    @Singleton
    fun getOptionsMenuUseCase() : MenuOptionsUseCase{
        return MenuOptionsUseCase()
    }

    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext mCtx:Context) : MyMentyDatabase{
        return Room.databaseBuilder(
            mCtx, MyMentyDatabase::class.java, Constants.DB_NAME
        ).enableMultiInstanceInvalidation().build()
    }

    @Provides
    fun getTipDao(db: MyMentyDatabase) : HealthTipDao{
        return db.healthTipDao()
    }

}