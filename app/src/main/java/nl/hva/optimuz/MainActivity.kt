package nl.hva.optimuz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    private var views = listOf<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_settings))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val loginButton = findViewById<Button>(R.id.login_button)
        val switchRegisterButton = findViewById<Button>(R.id.switch_to_register)
        val loginView = findViewById<TableLayout>(R.id.loginView)
        val registerView = findViewById<FrameLayout>(R.id.registerView)
        val mainView = findViewById<FrameLayout>(R.id.mainView)
        val feedback = findViewById<TextView>(R.id.feedback)
        views = listOf(loginView, registerView, mainView)

        loginButton.setOnClickListener {
            val loginEmail = findViewById<EditText>(R.id.login_email).text.toString()
            val loginPassword = findViewById<EditText>(R.id.login_password).text.toString()

            if (loginEmail == "email" && loginPassword == "pass"){
                switchToView(mainView)
            }else{
                feedback.text = "Invalid email/password combination"
            }
        }

        switchRegisterButton.setOnClickListener {
            switchToView(registerView)
        }

    }

    private fun switchToView(v: View): Boolean{
        if (views.contains(v)){
            for (view in views){
                view.isVisible(v === view)
            }
            findViewById<FrameLayout>(R.id.nav_view).isVisible(findViewById<FrameLayout>(R.id.mainView) === v) // toggle nav view
            return true
        }
        return false
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