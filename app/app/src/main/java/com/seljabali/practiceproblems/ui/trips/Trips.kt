package com.seljabali.practiceproblems.ui.trips


/**

Description of Question
Let's assume we have data about start and end time of Uber trips, e.g.

Trip 1: Start: 0, End: 5 // 12:00AM -> 12:05AM?
Trip 2: Start: 1, End: 2
Trip 3: Start: 3, End: 7


Based on this input we want to print number of trips in progress per boundary time:


[0,5) // we if search [5, 10],
[1,2)
[3, 7)


0th there is 1 trip
1st minute there is 2 trips


0 -> 1
1 -> 2
2 -> 1
3 -> 2
5 -> 1
7 -> 0


(0, 1) -> 1

Class Trip {
start
end
}

ArrayList<Trip> ~ sorted by start time
~ search: n(log(n)) to find start
~ once you find it, you keep moving until past start time

HashTable<Start> ~ key: start time, value: arraylist<Trip>,
// input (0, 3)
// implementation: lookup (0, 1, 2), aggregate count of arrayList per value returned
// complexity: (end_time - start_time) * (O(1) + O(addition))

// input (0, 3)
// first we look up 0 -> array returned, keep array count
// first we look up 1 -> array returned, keep array count
// first we look up 2 -> array returned, keep array count


 **/

import java.io.*
import java.util.*

class Trip(val startMinute: Int, val endMinute: Int) {

}

private val tripTable: HashMap<Int: ArrayList<Trip>> = HashMap()
private val tripCountTable: HashMap<Int: Int> = HashMap()

fun main(args: Array<Trip>): HashMap<Int: Int> {
    for (trip in args) {
        if (!tripTable.contains(trip.startMinute)) {
            tripTable[trip.startMinute] = ArrayList<Trip>()
        }
        tripTable[trip.startMinute] = tripTable[trip.startMinute].add(trip)
    }

    val tripCount = getTripCountWithinRange(0, 5)
    print("StartTime: 0, EndTime: 5 ${tripCount}")

    populateTripCountTable()
    return tripCountTable
}

fun getTripCountWithinRange(startMinute: Int, endMinute: Int): Int {
    if (endMinute - startMinute == 0) return 0
    var totalTrips = 0
    for (minute in startMinute..endMinute) {
        val arrayOfTripsDuringMinute = tripTable.get(minute)
        val tripsDuringThisMinute = arrayOfTripsDuringMinute.size
        totalTrips += tripsDuringThisMinute
    }
    return totalTrips
}

fun populateTripCountTable(): HashMap<Int: Int> {
    val startTime = getMinKeyValue(tripTable)
    val endTime = getMaxKeyValue(tripTable)
    for (startTime in startTime..endTime) {
        val arrayStartingAtTime = tripCountTable[startTime]
        tripCountTable[key] = arrayStartingAtTime?.size ?: 0
    }
}

fun getMinKeyValue(hashMap: HashMap<Int, Any>): Int {
    var minKey = -1
}

fun getMaxKeyValue(hashMap: HashMap<Int, Any>): Int {

}
