package com.hamza.ecommerce.utils;

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

import android.content.Context
import android.widget.Toast

// Extension function to show a toast message
fun Context.showToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


fun View.visibilityGone() {
    this.visibility = View.GONE
}

fun View.visibilityVisible() {
    this.visibility = View.VISIBLE
}

// to prevent multiple clicks on the back button multiple times
val NavHostController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

fun NavController.navigateSafely(
    @IdRes resID: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtra: Navigator.Extras? = null
) {
    val action = currentDestination?.getAction(resID) ?: graph.getAction(resID)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(resID, args, navOptions, navExtra)
    }
}


fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.length >= 6
}
