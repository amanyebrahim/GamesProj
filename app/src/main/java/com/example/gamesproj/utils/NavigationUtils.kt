package com.example.gamesproj.utils

import androidx.navigation.NavOptions
import com.example.gamesproj.R

/**
 * NavigationUtils
 */

//region Navigation options
val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

val navOptionsHomeBack = NavOptions.Builder()
        .setEnterAnim(R.anim.slide_in_right)
        .setExitAnim(R.anim.slide_out_left)
        .setPopEnterAnim(R.anim.slide_in_left)
        .setPopExitAnim(R.anim.slide_out_right)
        .setPopUpTo(R.id.homeFragment, false)
        .build()

val navOptionsNoBack = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .setPopUpTo(R.id.nav_graph, true)
    .setLaunchSingleTop(true)
    .build()
//endregion

//region Navigation keys
const val NAV_KEY_IMAGE_ID = "imageId"
const val NAV_KEY_TEXT_ID = "textId"

//endregion