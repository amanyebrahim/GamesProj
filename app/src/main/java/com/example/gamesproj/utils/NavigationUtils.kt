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
//endregion

//region Navigation keys
const val NAV_KEY_GAME_ID = "gameId"
//endregion