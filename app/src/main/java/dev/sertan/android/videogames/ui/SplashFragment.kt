package dev.sertan.android.videogames.ui

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.sertan.android.videogames.R

internal class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fullscreenMode(true)

        // Navigate to the home screen after 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val direction = SplashFragmentDirections.actionSplashToHome()
            findNavController().navigate(direction)
        }, 3000L)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fullscreenMode(false)
    }

    private fun fullscreenMode(isActive: Boolean) = requireActivity().window.run {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            val flag = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            if (isActive) addFlags(flag) else clearFlags(flag)
            return@run
        }

        val type = WindowInsets.Type.navigationBars()
        if (isActive) insetsController?.hide(type) else insetsController?.show(type)
    }

}
