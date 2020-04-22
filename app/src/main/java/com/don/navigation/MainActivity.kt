package com.don.navigation


import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //declare variable for appBarConfiguration
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup support action bar because we use theme without action bar
        setSupportActionBar(toolbar)

        // declare host variable for FragmentContainer
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return


        // create variable for navController
        val navController = host.navController

        // declare set of object id for app bar configuration
        // if we not set this, app will launch but without specific title bar which we already
        // describe on ``navigation`` folder `attribute LABEL`
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.loanScreen, R.id.accountScreen)
        )

        //setting up action bar with nav controller and app bar configuration
        setupActionBar(navController, appBarConfiguration)

        //set BottomNavigationBar with nav controller
        setupBottomNavigationBar(navController)

        // set destination changes on click listener based on id to string
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                destination.id.toString()
            }

            Toast.makeText(
                this@MainActivity, "Navigated to $dest",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }
    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun setupBottomNavigationBar(navController: NavController) {
        bottom_nav_view.setupWithNavController(navController)
    }

}