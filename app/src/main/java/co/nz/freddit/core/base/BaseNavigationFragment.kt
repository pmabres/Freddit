package co.nz.freddit.core.base

import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment

abstract class BaseNavigationFragment : BaseFragment() {
    fun nav() : NavController? {
        try {
            return NavHostFragment.findNavController(this)
        } catch (exception: Exception) {
            //Report crash to the analytics framework.
        }
        return null
    }

    fun navigate(@IdRes actionId: Int) {
        val navController = nav()
        if (navController?.currentDestination?.getAction(actionId) !== null) {
            navController.navigate(actionId)
        }
    }
    fun navigate(@NonNull directions: NavDirections) {
        val navController = nav()
        if (navController?.currentDestination?.getAction(directions.actionId) !== null) {
            navController.navigate(directions)
        }
    }
    fun navigate(@NonNull directions: NavDirections, @Nullable navOptions: NavOptions) {
        val navController = nav()
        if (navController?.currentDestination?.getAction(directions.actionId) !== null) {
            navController.navigate(directions, navOptions)
        }
    }
}
