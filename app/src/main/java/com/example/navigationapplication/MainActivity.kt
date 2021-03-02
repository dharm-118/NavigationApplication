package com.example.navigationapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var mfabbutton: FloatingActionButton
    private lateinit var mBottomNavView: BottomNavigationView
    private var mAddFragment: Fragment = AddFragment()
    private var mHomeFragment: Fragment = HomeFragment()
    private var mProfileFragment: Fragment = ProfileFragment()
    private var mSearchFragment: Fragment = SearchFragment()
    private var mSettingsFragment: Fragment = SettingsFragment()
    lateinit var drawerLayout: DrawerLayout
    lateinit var mToolbar: Toolbar
    lateinit var mNavView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mfabbutton = findViewById(R.id.uiButton)
        mBottomNavView = findViewById(R.id.bottomNavView)
        drawerLayout = findViewById(R.id.drawerLayout)
        mToolbar = findViewById(R.id.Toolbar)
        mNavView = findViewById(R.id.nav_view)

        replaceFragment(mHomeFragment)
        mfabbutton.setOnClickListener {
            replaceFragment(mAddFragment)
        }

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawerLayout,
            mToolbar, (R.string.open), (R.string.close)
        ) {

        }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        mNavView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(mHomeFragment)
                R.id.search -> replaceFragment(mSearchFragment)
                R.id.profile -> replaceFragment(mProfileFragment)
                R.id.settings -> replaceFragment(mSettingsFragment)
                else -> false
            }
            true
        }

        mBottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(mHomeFragment)
                R.id.search -> replaceFragment(mSearchFragment)
                R.id.settings -> replaceFragment(mSettingsFragment)
                R.id.profile -> replaceFragment(mProfileFragment)
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.commit()
        }
    }
}