package com.example.empoweher.firebaserepository

import com.example.empoweher.firebaseauth.AuthUser
import com.example.empoweher.firebaseproject.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override fun createUser(auth: AuthUser): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

    override fun loginUser(auth: AuthUser): Flow<ResultState<String>> {
        TODO("Not yet implemented")
    }

}