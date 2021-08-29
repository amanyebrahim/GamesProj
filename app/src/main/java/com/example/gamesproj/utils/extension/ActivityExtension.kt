package com.example.gamesproj.utils.extension

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.gamesproj.R

/**
 * ActivityExtension
 *
 */

/**
 * getNavController
 *
 * Return main navigation controller for the application.
 *
 * @return Instance of the application navigation controller.
 */
fun AppCompatActivity.getNavController(): NavController =
    Navigation.findNavController(this, R.id.nav_host_fragment)

/**
 * hideSoftKeyboard
 *
 * Close soft keyboard off screen.
 */
fun AppCompatActivity.hideSoftKeyboard() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    currentFocus?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
}