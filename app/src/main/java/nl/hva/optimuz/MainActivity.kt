package nl.hva.optimuz

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import nl.hva.optimuz.ui.account.AccountFragment
import nl.hva.optimuz.ui.home.HomeFragment
import nl.hva.optimuz.ui.medicine.MedicineOverviewFragment
import nl.hva.optimuz.ui.questionnaire_recycler.QuestionnaireRecFragment


class MainActivity : AppCompatActivity() {

    private val navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    openFragment(HomeFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_medication -> {
                    openFragment(MedicineOverviewFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
//                R.id.navigation_news -> {
//                    openFragment(NewsFragment.newInstance("", ""))
//                    return@OnNavigationItemSelectedListener true
//                }
                R.id.navigation_questionnaire -> {
                    openFragment(QuestionnaireRecFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    openFragment(AccountFragment.newInstance())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        openFragment(HomeFragment())
//        if (!State.loggedIn) {
//            navigateToFragment(R.id.navigation_login)
//        }
    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    // handle action bar back button
//    override fun onSupportNavigateUp(): Boolean {
//        // TODO: use navigateToFragment() ?
//        onBackPressed()
//        return true
//    }

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