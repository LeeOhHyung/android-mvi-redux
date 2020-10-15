package kr.ohyung.mvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.ohyung.mvi.databinding.ActivityRootBinding
import kr.ohyung.mvi.splash.SplashFragmentArgs

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_root)
        binding.lifecycleOwner = this

        val startArgs = SplashFragmentArgs(SPLASH_DURATION, SPLASH_IMAGE_QUERY).toBundle()
        navController = findNavController(R.id.nav_host_fragment)
        navController.setGraph(R.navigation.nav_graph, startArgs)
    }

    override fun onBackPressed() {
        if(navController.currentDestination?.id != R.id.fragment_bottom_navigation)
            super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()

    companion object {
        private const val SPLASH_DURATION: Long = 2500L
        private const val SPLASH_IMAGE_QUERY: String = "rain"
    }
}
