package com.example.empoweher

sealed class Screen(val route:String)
{
    object Login:Screen("login")
    object Login_Otp_check:Screen("login_otp_check")
}
