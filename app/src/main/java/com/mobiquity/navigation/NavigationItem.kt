package com.mobiquity.navigation

import com.mobiquity.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Help : NavigationItem("help", R.drawable.ic_help, "Help")
}