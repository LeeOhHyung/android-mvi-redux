package kr.ohyung.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.ohyung.mvi.databinding.ActivityRootBinding
import kr.ohyung.mvi.splash.SplashFragmentArgs
import kr.ohyung.mvi.utility.Constants

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_root)
        binding.lifecycleOwner = this

        val startArgs = SplashFragmentArgs(Constants.DURATION_SPLASH).toBundle()
        findNavController(R.id.nav_host_fragment).setGraph(R.navigation.nav_graph, startArgs)
    }
}
