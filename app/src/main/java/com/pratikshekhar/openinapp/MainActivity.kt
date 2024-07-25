package com.pratikshekhar.openinapp

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import com.pratikshekhar.openinapp.database.AuthorizationToken
import com.pratikshekhar.openinapp.databinding.ActivityMainBinding
import com.pratikshekhar.openinapp.di.SparkLineChart
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary)
binding.bottomNavigationView.setOnItemSelectedListener { item->
    when(item.itemId){
        R.id.action_link->replaceFragment(R.id.linksFragment)
        R.id.action_courses->replaceFragment(R.id.coursesFragment)
            R.id.action_campaigns->replaceFragment(R.id.campaignsFragment)
                R.id.action_profile->replaceFragment(R.id.profileFragment)
else -> replaceFragment(R.id.linksFragment)
    }

    true

}
    }
    private fun replaceFragment(@IdRes id:Int){
val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(id)

    }
}