package nl.hva.optimuz

import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_settings))
        val loginButton = findViewById<Button>(R.id.login_button)
        val loginView = findViewById<TableLayout>(R.id.loginView)
        val mainView = findViewById<FrameLayout>(R.id.mainView)
        val feedback = findViewById<TextView>(R.id.feedback)

        loginButton.setOnClickListener {
            val loginEmail = findViewById<EditText>(R.id.login_email).text.toString()
            val loginPassword = findViewById<EditText>(R.id.login_password).text.toString()

            if (loginEmail == "email" && loginPassword == "pass"){
                setupActionBarWithNavController(navController, appBarConfiguration)
                navView.setupWithNavController(navController)
                loginView.isVisible = false
                mainView.isVisible = true
                navView.isVisible = true
            }else{
                feedback.text = "Invalid email/password combination"
            }

        }

    }

}