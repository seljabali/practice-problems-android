package com.seljabali.practiceproblems.ui.islandproblem

import java.util.*
import kotlin.collections.ArrayList

class Coordinate(var x: Int, var y: Int)

enum class LandType {
    GROUND, WATER
}

class IslandProblem(private val type: Type) {
    enum class Type {
        Diagonal, Corners
    }

    fun getLandType(coordinate: Coordinate?): LandType? {
        return when (type) {
            Type.Diagonal -> {
                if (coordinate!!.x == 0 && coordinate.y == 0) {
                    return LandType.GROUND
                }
                if (coordinate.x == 1 && coordinate.y == 1) {
                    return LandType.GROUND
                }
                if (coordinate.x == 2 && coordinate.y == 2) {
                    LandType.GROUND
                } else LandType.WATER
            }
            Type.Corners -> {
                if (coordinate!!.x == 0 && coordinate.y == 0) {
                    return LandType.GROUND
                }
                if (coordinate.x == 0 && coordinate.y == 2) {
                    return LandType.GROUND
                }
                if (coordinate.x == 2 && coordinate.y == 0) {
                    return LandType.GROUND
                }
                if (coordinate.x == 2 && coordinate.y == 2) {
                    LandType.GROUND
                } else LandType.WATER
            }
        }
        return null
    }

}


class IslandSolution {
    private var islandProblem: IslandProblem? = null
    private var coordinates: Array<Array<Coordinate?>>

    private constructor() {}
    constructor(islandProblem: IslandProblem?) {
        this.islandProblem = islandProblem
        coordinates =
            Array(3) { arrayOfNulls<Coordinate>(3) }
        coordinates[0] =
            arrayOf(Coordinate(0, 0), Coordinate(0, 1), Coordinate(0, 2))
        coordinates[1] =
            arrayOf(Coordinate(1, 0), Coordinate(1, 1), Coordinate(1, 2))
        coordinates[2] =
            arrayOf(Coordinate(2, 0), Coordinate(2, 1), Coordinate(2, 2))
    }

    fun solve() { // Log.d() all results
    }

    companion object {
        private val TAG = IslandSolution::class.java.simpleName
    }
}

// To do
class IslandSolution {
    private var islandProblem: IslandProblem? = null
    private var coordinates: Array<Array<Coordinate?>>

    private constructor() {}
    constructor(islandProblem: IslandProblem?) {
        this.islandProblem = islandProblem
        coordinates =
            Array(3) { arrayOfNulls<Coordinate>(3) }
        coordinates[0] =
            arrayOf(Coordinate(0, 0), Coordinate(0, 1), Coordinate(0, 2))
        coordinates[1] =
            arrayOf(Coordinate(1, 0), Coordinate(1, 1), Coordinate(1, 2))
        coordinates[2] =
            arrayOf(Coordinate(2, 0), Coordinate(2, 1), Coordinate(2, 2))
    }

    fun solve() {
        val islandList: MutableList<Island> = ArrayList()
        for (i in 0 until getRowCount(coordinates)) {
            for (j in 0 until getColumnCount(coordinates)) {
                val candidate = coordinates[i][j]
                if (islandProblem!!.getLandType(candidate) == LandType.GROUND) {
                    if (!isInIslandList(candidate, islandList)) {
                        val newIsland = Island()
                        newIsland.addCoordinate(candidate)
                        newIsland.addAllCoordinate(
                            getRightNeighbors(
                                i,
                                j,
                                coordinates,
                                islandProblem
                            )
                        )
                        newIsland.addAllCoordinate(
                            getDownNeighbors(
                                i,
                                j,
                                coordinates,
                                islandProblem
                            )
                        )
                        newIsland.addAllCoordinate(
                            getLeftNeighbors(
                                i,
                                j,
                                coordinates,
                                islandProblem
                            )
                        )
                        newIsland.addAllCoordinate(
                            getDiagonalLeftNeighbors(
                                i,
                                j,
                                coordinates,
                                islandProblem
                            )
                        )
                        newIsland.addAllCoordinate(
                            getDiagonalRightNeighbors(
                                i,
                                j,
                                coordinates,
                                islandProblem
                            )
                        )
                        islandList.add(newIsland)
                    }
                }
            }
        }
        for (island in islandList) {
            Log.d(TAG, island.toString())
        }
    }

    private fun getRightNeighbors(
        row: Int, column: Int, coordinates: Array<Array<Coordinate?>>,
        wiki: IslandProblem?
    ): List<Coordinate?> {
        val coordinates1: ArrayList<Coordinate?> = ArrayList()
        for (i in column until getColumnCount(coordinates)) {
            val candidate = coordinates[row][i]
            if (wiki!!.getLandType(candidate) == LandType.GROUND) {
                coordinates1.add(candidate)
            } else {
                break
            }
        }
        return coordinates1
    }

    private fun getDownNeighbors(
        row: Int, column: Int, coordinates: Array<Array<Coordinate?>>,
        wiki: IslandProblem?
    ): List<Coordinate?> {
        val coordinates1: ArrayList<Coordinate?> = ArrayList()
        for (i in row until getRowCount(coordinates)) {
            val candidate = coordinates[i][column]
            if (wiki!!.getLandType(candidate) == LandType.GROUND) {
                coordinates1.add(candidate)
            } else {
                break
            }
        }
        return coordinates1
    }

    private fun getLeftNeighbors(
        row: Int, column: Int, coordinates: Array<Array<Coordinate?>>,
        wiki: IslandProblem?
    ): List<Coordinate?> {
        val coordinates1: ArrayList<Coordinate?> = ArrayList()
        for (i in column downTo 1) {
            val candidate = coordinates[row][i]
            if (wiki!!.getLandType(candidate) == LandType.GROUND) {
                coordinates1.add(candidate)
            } else {
                break
            }
        }
        return coordinates1
    }

    private fun getDiagonalLeftNeighbors(
        row: Int, column: Int,
        coordinates: Array<Array<Coordinate?>>, wiki: IslandProblem?
    ): List<Coordinate?> {
        val coordinates1: ArrayList<Coordinate?> = ArrayList()
        var i = row
        var j = column
        while (i > 0 && j > 0) {
            val candidate = coordinates[i][j]
            if (wiki!!.getLandType(candidate) == LandType.GROUND) {
                coordinates1.add(candidate)
            } else {
                break
            }
            i--
            j--
        }
        return coordinates1
    }

    private fun getDiagonalRightNeighbors(
        row: Int, column: Int,
        coordinates: Array<Array<Coordinate?>>, wiki: IslandProblem?
    ): List<Coordinate?> {
        val coordinates1: ArrayList<Coordinate?> = ArrayList()
        var i = row
        var j = column
        while (i < getRowCount(coordinates) && j < getColumnCount(
                coordinates
            )
        ) {
            val candidate = coordinates[i][j]
            if (wiki!!.getLandType(candidate) == LandType.GROUND) {
                coordinates1.add(candidate)
            } else {
                break
            }
            i++
            j++
        }
        return coordinates1
    }

    private fun isInIslandList(
        coordinate: Coordinate?,
        islandList: List<Island>
    ): Boolean {
        for (island in islandList) {
            if (island.hasCoordinate(coordinate)) {
                return true
            }
        }
        return false
    }

    companion object {
        private val TAG = IslandSolution::class.java.simpleName
        private fun <T> getColumnCount(list: Array<Array<T>>): Int {
            return list[0].size
        }

        private fun <T> getRowCount(list: Array<Array<T>>): Int {
            return list.size
        }
    }
}

class Island {
    private val islandCoordinates: MutableSet<Coordinate?> = TreeSet()
    fun addCoordinate(coordinate: Coordinate?) {
        islandCoordinates.add(coordinate)
    }

    fun addAllCoordinate(coordinates: List<Coordinate?>?) {
        islandCoordinates.addAll(coordinates!!)
    }

    fun hasCoordinate(coordinate: Coordinate?): Boolean {
        for (coordinate1 in islandCoordinates) {
            if (coordinate == coordinate1) {
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        return if (islandCoordinates.isEmpty()) {
            "Empty island."
        } else {
            var result =
                "Island has " + islandCoordinates.size + " coordinates. \n\t"
            for (coordinate in islandCoordinates) {
                result += "(" + coordinate!!.x.toString() + "," + coordinate.y.toString() + "), "
            }
            result
        }
    }
}