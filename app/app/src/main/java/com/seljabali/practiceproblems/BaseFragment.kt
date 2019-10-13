package com.seljabali.practiceproblems

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {

    protected val baseActivity get() = activity as BaseActivity
    private val compositeDisposable = CompositeDisposable()

    override fun onPause() {
        super.onPause()
        clearSubscriptions()
    }

    protected fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun clearSubscriptions() {
        compositeDisposable.clear()
    }
}