package com.example.scrollablexml

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.scrollablexml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val menuFragment = MenuFragment()
        val fragment = fragmentManager.findFragmentByTag(MenuFragment::class.java.simpleName)
        if (fragment !is MenuFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + MenuFragment::class.java.simpleName)
            fragmentManager
                .beginTransaction()
                .add(binding.fragmentContainer.id, menuFragment, MenuFragment::class.java.simpleName)
                .commit()
        }
    }
}