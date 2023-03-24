package com.example.aidlcilent.view

sealed class Screen(val route:String) {
    object mainscreen : Screen("MainScreen")
    object nextScreen : Screen("NextScreen")
}
