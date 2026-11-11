package com.mobile.tenantmanagementsys.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mobile.tenantmanagementsys.R
import com.mobile.tenantmanagementsys.databinding.ActivityMainBinding
import com.mobile.tenantmanagementsys.databinding.NavHeaderBinding
import com.mobile.tenantmanagementsys.ui.auth.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Controls navigation between Fragments.
    private lateinit var navController: NavController

    // Defines which screens are top-level destinations.
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("TENANT_LIFECYCLE", "MainActivity: onCreate")

        // Load the Toolbar and NavHost from activity_main.xml.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Use our Toolbar as the Activity's App Bar.
        setSupportActionBar(binding.toolbar)

        // Find the NavHost that displays the Fragments.
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Get the controller that switches between destinations.
        navController = navHostFragment.navController

        // Configure the App Bar using the navigation graph.
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.dashboardFragment, R.id.addTenantFragment, R.id.viewTenantsFragment),
            binding.drawerLayout
        )

        // Automatically update the toolbar title and back arrow.
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )
        binding.navView.setupWithNavController(navController)

        val email = intent.getStringExtra("EMAIL")

        if (binding.navView.headerCount > 0) {
            val headerBinding = NavHeaderBinding.bind(
                binding.navView.getHeaderView(0)
            )
            headerBinding.headerEmailTextView.text = email ?: "Signed in"
        }
    }

    // Make the App Bar's back arrow work.
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            return true
        }

        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    // Activity lifecycle logs from Lab 6.

    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onDestroy")
    }
}