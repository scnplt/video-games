package dev.sertan.android.videogames.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.sertan.android.videogames.R
import dev.sertan.android.videogames.data.service.analytics.AnalyticsService
import dev.sertan.android.videogames.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
internal class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {
    private lateinit var binding: ActivityMainBinding

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    }

    private val navController: NavController by lazy {
        navHostFragment.navController.also { it.addOnDestinationChangedListener(this) }
    }

    private val currentFragmentName: String?
        get() = navHostFragment.childFragmentManager.fragments[0]::class.simpleName

    @Inject
    lateinit var analytics: AnalyticsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavView.setupWithNavController(navController)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        binding.bottomNavView.visibility = when (destination.id) {
            R.id.homeFragment -> View.VISIBLE
            R.id.favoritesFragment -> View.VISIBLE
            else -> View.GONE
        }

        analytics.logScreenChange(
            previousScreen = "$currentFragmentName",
            targetScreen = destination.label.toString()
        )
    }

}
