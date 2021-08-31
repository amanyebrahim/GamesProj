package com.example.gamesproj.utils.extension


import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import timber.log.Timber

/**
 * ViewExtension
 */

/**
 * Get parent activity from given view.
 */
fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context

    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }

        context = context.baseContext
    }

    return null
}
fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInVisible() {
    visibility = View.INVISIBLE
}
fun View.makeGone() {
    visibility = View.GONE
}
/*
 * Sets View visibility due to condition
 */
fun View.showIf(statement: Boolean?) {
    this.visibility = if (statement != null && statement) View.VISIBLE else View.GONE
}
/**
 * Add window insets listener
 * and use the returned insets to get status and navigation bars heights
 * and use them as padding to avoid application being cut by status and navigation bars.
 *
 * @param topView View to set padding top to.
 * @param bottomView View to set padding bottom to.
 * @param topBottomView View to set padding top and bottom to.
 */
fun View.setInsetsPadding(
    topView: View? = null,
    bottomView: View? = null,
    topBottomView: View? = null
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        val statusBarsInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
        Timber.v("statusBarsInsets: $statusBarsInsets")
        val navigationBarsInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
        Timber.v("navigationBarsInsets: $navigationBarsInsets")
        topView?.setPadding(0, statusBarsInsets.top, 0, 0)
        bottomView?.setPadding(0, 0, 0, navigationBarsInsets.bottom)
        topBottomView?.setPadding(0, statusBarsInsets.top, 0, navigationBarsInsets.bottom)
        return@setOnApplyWindowInsetsListener WindowInsetsCompat.CONSUMED
    }
}