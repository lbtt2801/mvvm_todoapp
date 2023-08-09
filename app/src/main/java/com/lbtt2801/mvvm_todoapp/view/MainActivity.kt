package com.lbtt2801.mvvm_todoapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.lbtt2801.mvvm_todoapp.R
import com.lbtt2801.mvvm_todoapp.databinding.ActivityMainBinding
import com.lbtt2801.mvvm_todoapp.model.AppToDoTasks
import com.lbtt2801.mvvm_todoapp.model.IPassData
import com.lbtt2801.mvvm_todoapp.model.Task
import com.lbtt2801.mvvm_todoapp.viewmodel.HomeViewModel


class MainActivity : AppCompatActivity(), IPassData {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var filter = "all"

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
    }

    private val navController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_statistic
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        replaceFragment(HomeFragment())
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_clear -> {
                AppToDoTasks.todoTasks.retainAll{ it.isCompleted == false}
                replaceFragment()
                true
            }
            R.id.action_refresh -> {
                AppToDoTasks.todoTasks.clear()
                replaceFragment()
                true
            }
            R.id.action_all -> {
                filter = "all"
                replaceFragment()
                true
            }
            R.id.action_action -> {
                filter = "action"
                replaceFragment()
                true
            }
            R.id.action_completed -> {
                filter = "complete"
                replaceFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun replaceFragment() {
//        when (destination) {
//            NavDestination.HOME -> {
//                navController.navigate(R.id.action_nav_home_to_nav_statistic)
//            }
//            else -> Unit
//        }

        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.nav_host_fragment_content_main, HomeFragment())
        fragmentTransition.commit()
//        customToolbar("")
    }

//    fun sendData(): MutableList<Task> {
//        return AppToDoTasks.todoTasks
//    }

    fun filterData(): String {
        return filter
    }

    override fun exchangeData(title: String, details: String) {
        AppToDoTasks.todoTasks.add(Task(title,details,false))
    }

    override fun changeDataItem(title: String, details: String) {
        AppToDoTasks.todoTasks.set(AppToDoTasks.position,Task(title,details,false))
    }
}