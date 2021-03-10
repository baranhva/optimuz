package nl.hva.optimuz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    var publicFragments = listOf(R.id.navigation_login, R.id.navigation_register)
    var privateFragments = listOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_settings)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_settings))
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_settings, R.id.navigation_login))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        navView.isInvisible = true
        if (!State.loggedIn){
            switchFragment(R.id.navigation_login)
        }
    }

    fun switchFragment(id: Int){

        if (!State.loggedIn && privateFragments.contains(id)){
            return
        }

        findNavController(R.id.nav_host_fragment).navigate(id)

        findViewById<BottomNavigationView>(R.id.nav_view).isVisible(privateFragments.contains(id))
    }

    private fun View.isVisible(visible: Boolean){
        if (visible){
            this.isVisible = true
        }else{
            this.isInvisible = true
        }
    }

    // auto close keyboard
    @SuppressLint("ServiceCast")
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}