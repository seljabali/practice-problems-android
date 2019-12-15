package com.seljabali.practiceproblems.ui.clock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seljabali.practiceproblems.BaseFragment
import com.seljabali.practiceproblems.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_clock.*
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatterBuilder
import java.util.*
import java.util.concurrent.TimeUnit

class ClockFragment : BaseFragment() {

    companion object {
        val TAG = ClockFragment::class.java.simpleName
    }

    private val timeInterval = TIME_INTERVAL.SECONDS
    private val timeIntervalMultiplier = 2

    private val HH_MM_SS_SSS_AM = "hh:mm:ss.SSS a"
    private val period = getNanoSeconds(timeInterval, timeIntervalMultiplier)
    private val timeObservable = Observable.interval(1, TimeUnit.SECONDS)
    private var currentTime: ZonedDateTime = NOW()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_clock, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTime(currentTime.print(HH_MM_SS_SSS_AM))
        subscribe(timeObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                currentTime = currentTime.plusNanos(period)
                setTime(currentTime.print(HH_MM_SS_SSS_AM))
            }
        )
    }

    private fun getNanoSeconds(timeInterval: TIME_INTERVAL, timeIntervalMultiplier: Int): Long {
        return when (timeInterval) {
            TIME_INTERVAL.MILLISECONDS -> (timeIntervalMultiplier * 10000000).toLong()
            TIME_INTERVAL.SECONDS -> (timeIntervalMultiplier * 1000000000).toLong()
        }
    }

    private fun setTime(time: String) {
        tvClock.text = time
    }

    private fun ZonedDateTime.print(format: String): String =
        this.format(DateTimeFormatterBuilder().appendPattern(format).toFormatter(Locale.US))

    private fun NOW(): ZonedDateTime = ZonedDateTime.now(getDefaultZoneId())

    private fun getDefaultZoneId(): ZoneId =
        try {
            ZoneId.systemDefault()
        } catch (e: Exception) {
            null
        } ?: ZoneId.of("America/Montreal")

}


enum class TIME_INTERVAL {
    MILLISECONDS, SECONDS
}