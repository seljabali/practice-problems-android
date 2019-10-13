package com.seljabali.practiceproblems.ui.landingpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R
import com.seljabali.practiceproblems.ui.HomeActivity
import com.seljabali.practiceproblems.ui.anagram.AnagramFragment
import com.seljabali.practiceproblems.ui.animation.AnimationFragment
import com.seljabali.practiceproblems.ui.htree.HTreeFragment
import com.seljabali.practiceproblems.ui.phonedialer.PhoneDialerFragment
import kotlinx.android.synthetic.main.fragment_landing_page.*

class LandingPageFragment : BaseFragment() {

    companion object {
        val TAG: String = LandingPageFragment::class.java.simpleName
        fun newInstance(): LandingPageFragment =
            LandingPageFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(
        R.layout.fragment_landing_page, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        anagramButton.setOnClickListener { showFragment(AnagramFragment(), AnagramFragment.TAG) }
        htreeButton.setOnClickListener { showFragment(HTreeFragment(), HTreeFragment.TAG) }
        phoneDialerButton.setOnClickListener { showFragment(PhoneDialerFragment(), PhoneDialerFragment.TAG) }
        animationButton.setOnClickListener { showFragment(AnimationFragment(), AnimationFragment.TAG) }
    }

    fun showFragment(baseFragment: BaseFragment, tag: String) {
        (baseActivity as HomeActivity).showFragment(baseFragment, tag)
    }
}