package com.example.flow_retrofit.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.flow_retrofit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        navController = findNavController(R.id.nav_host_fragment_container)
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.homeFragment
//            )
//        )

//        setupActionBarWithNavController(
//            navController, appBarConfiguration
//        )

    }

//    override fun onSupportNavigateUp(): Boolean {
//        return super.onSupportNavigateUp() || navController.navigateUp()
//    }
}