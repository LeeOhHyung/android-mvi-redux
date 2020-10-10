package kr.ohyung.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.ohyung.mvi.databinding.ActivityRootBinding
import kr.ohyung.mvi.splash.SplashFragmentArgs

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_root)
        binding.lifecycleOwner = this

        val startArgs = SplashFragmentArgs(SPLASH_DURATION, SPLASH_IMAGE_QUERY).toBundle()
        val navController = findNavController(R.id.nav_host_fragment)
        navController.setGraph(R.navigation.nav_graph, startArgs)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            // can set about state bar or action bar, etc when navigating fragments.
        }
    }

    companion object {
        private const val SPLASH_DURATION: Long = 2500L
        private const val SPLASH_IMAGE_QUERY: String = "weather"
    }
}
