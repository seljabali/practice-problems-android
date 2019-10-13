package com.seljabali.practiceproblems.ui.animation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import android.widget.TextView
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R
import kotlinx.android.synthetic.main.fragment_animation.*

class AnimationFragment: BaseFragment() {

    companion object {
        @JvmField
        val TAG: String = AnimationFragment::class.java.simpleName

        const val ONE_EIGHTY: Float = 180f

        const val LONGER_DELAY_MS: Long = 1200
        const val SHORTER_DELAY_MS: Long = 400

        const val REGULAR_DURATION_MS: Long = 1200

        const val MOVE_X_DELTA_COORDINATE: Float = 64f
        const val MOVE_Y_DELTA_COORDINATE: Float = 34f
    }

    private var startingPX: Float = 0f
    private var startingPY: Float = 0f
    private var startingDX: Float = 0f
    private var startingDY: Float = 0f
    private lateinit var animatorSet: AnimatorSet

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_animation, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnMeasureCallback()
    }

    private fun onViewReady() {
        measureScreen()
        playAnimationsTogether(arrayListOf(getAnimationOfP(), getAnimationOfD()))
    }

    override fun onStop() {
        super.onStop()
        firstLetterTextView.clearAnimation()
        secondLetterTextView.clearAnimation()
        animatorSet.cancel()
    }

    private fun setOnMeasureCallback() {
        view?.viewTreeObserver?.apply {
            addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    removeOnGlobalLayoutListener(this)
                    onViewReady()
                }
            })
        }
    }

    private fun removeOnGlobalLayoutListener(listener: ViewTreeObserver.OnGlobalLayoutListener) {
        view?.viewTreeObserver?.removeOnGlobalLayoutListener(listener)
    }

    private fun playAnimationsTogether(animator: Collection<ObjectAnimator>) {
        animatorSet = AnimatorSet()
        animatorSet.playTogether(animator)
        animatorSet.start()
    }

    private fun measureScreen() {
        view?.let {
            it.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            val midScreenX = it.width / 2f
            val midScreenY = it.height/ 2f
            startingPX = midScreenX - MOVE_Y_DELTA_COORDINATE
            startingDX = midScreenX + MOVE_X_DELTA_COORDINATE
            startingPY = midScreenY + MOVE_Y_DELTA_COORDINATE
            startingDY = midScreenY - MOVE_Y_DELTA_COORDINATE
            firstLetterTextView.x = startingPX
            firstLetterTextView.y = startingPY
            secondLetterTextView.x = startingDX
            secondLetterTextView.y = startingDY
            it.invalidate()
        }
    }

    private fun getAnimationOfP(): ObjectAnimator {
        val pAnimationA = getYAxisFlipAnimation(firstLetterTextView, ONE_EIGHTY)
        pAnimationA.startDelay = LONGER_DELAY_MS
        val pAnimationA2 = getXMoveToAnimation(firstLetterTextView, startingDX)

        val pAnimationB = getXAxisFlipAnimation(firstLetterTextView, ONE_EIGHTY)
        val pAnimationB2 = getYMoveToAnimation(firstLetterTextView, startingDY)

        val pAnimationC = getYAxisFlipAnimation(firstLetterTextView, 0f)
        pAnimationC.startDelay = SHORTER_DELAY_MS
        val pAnimationC2 = getXMoveToAnimation(firstLetterTextView, startingPX)

        val pAnimationD = getXAxisFlipAnimation(firstLetterTextView, 0f)
        val pAnimationD2 = getYMoveToAnimation(firstLetterTextView, startingPY)

        // 3->4th Quadrant
        pAnimationA.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                pAnimationA2.start()
            }
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                pAnimationB.start()
            }
            override fun onAnimationCancel(animation: Animator) {
                pAnimationA2.cancel()
                pAnimationB.cancel()
                pAnimationC.cancel()
                pAnimationD.cancel()
            }
        })
        // 4->1st Quadrant
        pAnimationB.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                pAnimationB2.start()
            }
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                pAnimationC.start()
            }
            override fun onAnimationCancel(animation: Animator) {
                pAnimationA.cancel()
                pAnimationB2.cancel()
                pAnimationC.cancel()
                pAnimationD.cancel()
            }
        })
        // 1->2nd Quadrant
        pAnimationC.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                pAnimationC2.start()
            }
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                pAnimationD.start()
            }
            override fun onAnimationCancel(animation: Animator) {
                pAnimationA.cancel()
                pAnimationB.cancel()
                pAnimationC2.cancel()
                pAnimationD.cancel()
            }
        })
        // 2->3rd Quadrant
        pAnimationD.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                pAnimationD2.start()
            }
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                pAnimationA.start()
            }
            override fun onAnimationCancel(animation: Animator) {
                pAnimationA.cancel()
                pAnimationB.cancel()
                pAnimationC.cancel()
                pAnimationD2.cancel()
            }
        })
        return pAnimationA
    }

    private fun getAnimationOfD(): ObjectAnimator {
        val dAnimationA = getYAxisFlipAnimation(secondLetterTextView, ONE_EIGHTY)
        val dAnimationA2 = getXMoveToAnimation(secondLetterTextView, startingPX)
        dAnimationA.startDelay = LONGER_DELAY_MS

        val dAnimationB = getXAxisFlipAnimation(secondLetterTextView, ONE_EIGHTY)
        val dAnimationB2 = getYMoveToAnimation(secondLetterTextView, startingPY)

        val dAnimationC = getYAxisFlipAnimation(secondLetterTextView, 0f)
        dAnimationC.startDelay = SHORTER_DELAY_MS
        val dAnimationC2 = getXMoveToAnimation(secondLetterTextView, startingDX)

        val dAnimationD = getXAxisFlipAnimation(secondLetterTextView, 0f)
        val dAnimationD2 = getYMoveToAnimation(secondLetterTextView, startingDY)

        // 1->2nd Quadrant
        dAnimationA.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                dAnimationA2.start()
            }
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                dAnimationB.start()
            }
            override fun onAnimationCancel(animation: Animator) {
                dAnimationA2.cancel()
                dAnimationB.cancel()
                dAnimationC.cancel()
                dAnimationD.cancel()
            }
        })
        // 2->3rd Quadrant
        dAnimationB.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                dAnimationB2.start()
            }
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                dAnimationC.start()
            }
            override fun onAnimationCancel(animation: Animator) {
                dAnimationA.cancel()
                dAnimationB2.cancel()
                dAnimationC.cancel()
                dAnimationD.cancel()
            }
        })
        // 3->4th Quadrant
        dAnimationC.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                dAnimationC2.start()
            }
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                dAnimationD.start()
            }
            override fun onAnimationCancel(animation: Animator) {
                dAnimationA.cancel()
                dAnimationB.cancel()
                dAnimationC2.cancel()
                dAnimationD.cancel()
            }
        })
        // 4->1st Quadrant
        dAnimationD.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                dAnimationD2.start()
            }
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                dAnimationA.start()
            }
            override fun onAnimationCancel(animation: Animator) {
                dAnimationA.cancel()
                dAnimationB.cancel()
                dAnimationC.cancel()
                dAnimationD2.cancel()
            }
        })
        return dAnimationA
    }

    private fun getXMoveToAnimation(textView: TextView, moveToX: Float, duration: Long = REGULAR_DURATION_MS): ObjectAnimator {
        val moveLtoRByAnimation = ObjectAnimator.ofFloat(textView, getTextViewXMoveToProperty(), moveToX)
        moveLtoRByAnimation.duration = duration
        return moveLtoRByAnimation
    }

    private fun getYMoveToAnimation(textView: TextView, moveToY: Float, duration: Long = REGULAR_DURATION_MS): ObjectAnimator {
        val moveLtoRByAnimation = ObjectAnimator.ofFloat(textView, getTextViewYMoveToProperty(), moveToY)
        moveLtoRByAnimation.duration = duration
        return moveLtoRByAnimation
    }

    private fun getYAxisFlipAnimation(textView: TextView, rotation: Float, duration: Long = REGULAR_DURATION_MS): ObjectAnimator {
        val horizontalFlipAnimation = ObjectAnimator.ofFloat(textView, getTextViewYAxisFlipProperty(), rotation)
        horizontalFlipAnimation.duration = duration
        return horizontalFlipAnimation
    }

    private fun getXAxisFlipAnimation(textView: TextView, rotation: Float, duration: Long = REGULAR_DURATION_MS): ObjectAnimator {
        val verticalFlipAnimation = ObjectAnimator.ofFloat(textView, getTextViewXAxisFlipProperty(), rotation)
        verticalFlipAnimation.duration = duration
        return verticalFlipAnimation
    }

    private fun getTextViewXMoveToProperty(): Property<TextView, Float> {
        return object : Property<TextView, Float>(Float::class.javaPrimitiveType, "textXLocation") {
            override fun get(`object`: TextView): Float? {
                return `object`.x
            }

            override fun set(`object`: TextView, value: Float) {
                `object`.x = value
            }
        }
    }

    private fun getTextViewYMoveToProperty(): Property<TextView, Float> {
        return object : Property<TextView, Float>(Float::class.javaPrimitiveType, "textYLocation") {
            override fun get(`object`: TextView): Float {
                return `object`.y
            }

            override fun set(`object`: TextView, value: Float) {
                `object`.y = value
            }
        }
    }

    private fun getTextViewYAxisFlipProperty(): Property<TextView, Float> {
        return object : Property<TextView, Float>(Float::class.javaPrimitiveType, "textRotationY") {
            override fun get(`object`: TextView): Float {
                return `object`.rotationY
            }

            override fun set(`object`: TextView, value: Float) {
                `object`.rotationY = value
            }
        }
    }

    private fun getTextViewXAxisFlipProperty(): Property<TextView, Float> {
        return object : Property<TextView, Float>(Float::class.javaPrimitiveType, "textRotationX") {
            override fun get(`object`: TextView): Float {
                return `object`.rotationX
            }

            override fun set(`object`: TextView, value: Float) {
                `object`.rotationX = value
            }
        }
    }
}