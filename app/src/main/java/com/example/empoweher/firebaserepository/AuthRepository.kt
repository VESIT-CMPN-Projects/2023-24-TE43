package com.example.empoweher.firebaserepository

import com.example.empoweher.firebaseauth.AuthUser
import com.example.empoweher.firebaseproject.ResultState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun createUser(
        auth: AuthUser
    ) : Flow<ResultState<String>>

    fun loginUser(
        auth: AuthUser
    ) : Flow<ResultState<String>>
}