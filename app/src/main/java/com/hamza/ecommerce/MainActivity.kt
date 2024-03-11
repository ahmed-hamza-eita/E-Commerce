package com.hamza.ecommerce

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import com.hamza.ecommerce.data.repository.user.UserPreferenceRepository
import com.hamza.ecommerce.data.repository.user.UserPreferenceRepositoryImpl
import com.hamza.ecommerce.ui.auth.AuthActivity
import com.hamza.ecommerce.ui.common.viewmodels.UserViewModel
import com.hamza.ecommerce.ui.common.viewmodels.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserPreferenceRepositoryImpl(this@MainActivity))
    }
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        initSplash()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isUserLoggedIn()
    }

    private fun isUserLoggedIn() {
        lifecycleScope.launch(Dispatchers.Main) {
            val isLoggedIn = userViewModel.isUserLoggedIn()
            Log.d(TAG, "onCreate: isLoggedIn: $isLoggedIn")
            if (isLoggedIn) {
                setContentView(R.layout.activity_main)
            } else {
             userViewModel.setLoggedInStatus(true)
                goToAuthActivity()
            }
        }
    }

    private fun goToAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val options = ActivityOptions.makeCustomAnimation(
            this, android.R.anim.fade_in, android.R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }

    @SuppressLint("Recycle")
    @RequiresApi(Build.VERSION_CODES.S)
    private fun initSplash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 1000L
                slideUp.doOnEnd { splashScreenView.remove() }
                slideUp.start()
            }
        } else {
            setTheme(R.style.Theme_ECommerce)
        }
    }
    companion object{
        private const val TAG = "MainActivity"
    }

}