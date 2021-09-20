package com.jiajia.random

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var show: Boolean = false

    lateinit var blankFragment: BlankFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blankFragment = BlankFragment()

        btnShowHide.setOnClickListener {
            show = !show
            if (show) {
                showFragment()
            } else {
                hideFragment()
            }
        }

    }

    private fun hideFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(BlankFragment.TAG)
        fragment?.let {
            supportFragmentManager.beginTransaction().hide(it).commit()
        }
    }

    private fun showFragment() {
        val fragment = supportFragmentManager.findFragmentByTag(BlankFragment.TAG)
        if (fragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragment_container,
                blankFragment, BlankFragment.TAG).commit()
        } else {
            supportFragmentManager.beginTransaction().show(fragment).commit()
        }
    }
}