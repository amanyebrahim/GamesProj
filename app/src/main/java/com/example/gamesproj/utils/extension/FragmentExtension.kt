package com.example.gamesproj.utils.extension



import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.gamesproj.R



/**
 * FragmentExtension
 */
/**
 * getNavController
 *
 * Return main navigation controller for the application.
 *
 * @return Instance of the application navigation controller.
 */
fun Fragment.getNavController(): NavController? {
    val activity = this.activity

    activity?.let {
        return Navigation.findNavController(it, R.id.nav_host_fragment)
    }

    return null
}
/**
 * Show message in toast.
 *
 * @param message  The message to display in toast.
 */
fun Fragment.showMessageToast(message: String?) {
    message?.let { nonNullMessage ->
        val context = this.context

        context?.let {
            Toast.makeText(it, nonNullMessage.trim(), Toast.LENGTH_LONG).show()
        }
    }
}
