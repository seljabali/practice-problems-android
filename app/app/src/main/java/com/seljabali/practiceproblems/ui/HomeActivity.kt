package com.seljabali.practiceproblems.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.seljabali.practiceproblems.BaseActivity
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R
import com.seljabali.practiceproblems.ui.landingpage.LandingPageFragment

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar) ?: return
        setSupportActionBar(toolbar)
        showLandingPage()
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    fun showFragment(baseFragment: BaseFragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .add(R.id.frameLayout, baseFragment)
            .addToBackStack(tag)
            .commit()
    }

    private fun showLandingPage() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, LandingPageFragment.newInstance())
            .commit()
    }

}
