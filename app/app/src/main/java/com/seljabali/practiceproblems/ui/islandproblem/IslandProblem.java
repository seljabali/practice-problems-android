package com.seljabali.practiceproblems.ui.islandproblem;

import android.util.ArraySet;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Coordinate {

    public int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public enum LandType {
    GROUND, WATER
}

public class IslandProblem {

    public enum Type { Diagonal, Corners}

    private Type type;
    public IslandProblem(@NonNull Type type) {
        this.type = type;
    }

    public LandType getLandType(Coordinate coordinate) {
        switch (this.type) {
            case Diagonal:
                if (coordinate.x == 0 && coordinate.y == 0) {
                    return LandType.GROUND;
                }
                if (coordinate.x == 1 && coordinate.y == 1) {
                    return LandType.GROUND;
                }
                if (coordinate.x == 2 && coordinate.y == 2) {
                    return LandType.GROUND;
                }
                return LandType.WATER;

            case Corners: {
                if (coordinate.x == 0 && coordinate.y == 0) {
                    return LandType.GROUND;
                }
                if (coordinate.x == 0 && coordinate.y == 2) {
                    return LandType.GROUND;
                }
                if (coordinate.x == 2 && coordinate.y == 0) {
                    return LandType.GROUND;
                }
                if (coordinate.x == 2 && coordinate.y == 2) {
                    return LandType.GROUND;
                }
                return LandType.WATER;
            }
        }
        return null;
    }
}


public class IslandSolution {
    private static final String TAG = IslandSolution.class.getSimpleName();

    private IslandProblem islandProblem;
    private Coordinate[][] coordinates;

    private IslandSolution() {}
    public IslandSolution(IslandProblem islandProblem) {
        this.islandProblem = islandProblem;
        this.coordinates = new Coordinate[3][3];
        coordinates[0] = new Coordinate[]{new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(0, 2)};
        coordinates[1] = new Coordinate[]{new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(1, 2)};
        coordinates[2] = new Coordinate[]{new Coordinate(2, 0), new Coordinate(2, 1), new Coordinate(2, 2)};
    }

    public void solve() {
// Log.d() all results
    }
}

// To do
public class IslandSolution {
    private static final String TAG = IslandSolution.class.getSimpleName();

    private IslandProblem islandProblem;
    private Coordinate[][] coordinates;

    private IslandSolution() {}
    public IslandSolution(IslandProblem islandProblem) {
        this.islandProblem = islandProblem;
        this.coordinates = new Coordinate[3][3];
        coordinates[0] = new Coordinate[]{new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(0, 2)};
        coordinates[1] = new Coordinate[]{new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(1, 2)};
        coordinates[2] = new Coordinate[]{new Coordinate(2, 0), new Coordinate(2, 1), new Coordinate(2, 2)};
    }

    public void solve() {
        List<Island> islandList = new ArrayList<>();
        for (int i = 0; i < getRowCount(coordinates); i++) {
            for (int j = 0; j < getColumnCount(coordinates); j++) {
                Coordinate candidate = coordinates[i][j];
                if (islandProblem.getLandType(candidate) == LandType.GROUND) {
                    if (!isInIslandList(candidate, islandList)) {
                        Island newIsland = new Island();
                        newIsland.addCoordinate(candidate);
                        newIsland.addAllCoordinate(getRightNeighbors(i, j, coordinates, islandProblem));
                        newIsland.addAllCoordinate(getDownNeighbors(i, j, coordinates, islandProblem));
                        newIsland.addAllCoordinate(getLeftNeighbors(i, j, coordinates, islandProblem));
                        newIsland.addAllCoordinate(getDiagonalLeftNeighbors(i, j, coordinates, islandProblem));
                        newIsland.addAllCoordinate(getDiagonalRightNeighbors(i, j, coordinates, islandProblem));
                        islandList.add(newIsland);
                    }
                }
            }
        }
        for (Island island : islandList) {
            Log.d(TAG, island.toString());
        }
    }

    private List<Coordinate> getRightNeighbors(int row, int column, Coordinate[][] coordinates,
                                               IslandProblem wiki) {
        ArrayList<Coordinate> coordinates1 = new ArrayList<>();
        for (int i = column; i < getColumnCount(coordinates); i++) {
            Coordinate candidate = coordinates[row][i];
            if (wiki.getLandType(candidate) == LandType.GROUND) {
                coordinates1.add(candidate);
            } else {
                break;
            }
        }
        return coordinates1;
    }

    private List<Coordinate> getDownNeighbors(int row, int column, Coordinate[][] coordinates,
                                              IslandProblem wiki) {
        ArrayList<Coordinate> coordinates1 = new ArrayList<>();
        for (int i = row; i < getRowCount(coordinates); i++) {
            Coordinate candidate = coordinates[i][column];
            if (wiki.getLandType(candidate) == LandType.GROUND) {
                coordinates1.add(candidate);
            } else {
                break;
            }
        }
        return coordinates1;
    }

    private List<Coordinate> getLeftNeighbors(int row, int column, Coordinate[][] coordinates,
                                              IslandProblem wiki) {
        ArrayList<Coordinate> coordinates1 = new ArrayList<>();
        for (int i = column; i > 0; i--) {
            Coordinate candidate = coordinates[row][i];
            if (wiki.getLandType(candidate) == LandType.GROUND) {
                coordinates1.add(candidate);
            } else {
                break;
            }
        }
        return coordinates1;
    }

    private List<Coordinate> getDiagonalLeftNeighbors(int row, int column,
                                                      Coordinate[][] coordinates, IslandProblem wiki) {
        ArrayList<Coordinate> coordinates1 = new ArrayList<>();
        for (int i = row, j = column; i > 0 && j > 0; i--, j--) {
            Coordinate candidate = coordinates[i][j];
            if (wiki.getLandType(candidate) == LandType.GROUND) {
                coordinates1.add(candidate);
            } else {
                break;
            }
        }
        return coordinates1;
    }

    private List<Coordinate> getDiagonalRightNeighbors(int row, int column,
                                                       Coordinate[][] coordinates, IslandProblem wiki) {
        ArrayList<Coordinate> coordinates1 = new ArrayList<>();
        for (int i = row, j = column; i < getRowCount(coordinates) && j < getColumnCount(
                coordinates); i++, j++) {
            Coordinate candidate = coordinates[i][j];
            if (wiki.getLandType(candidate) == LandType.GROUND) {
                coordinates1.add(candidate);
            } else {
                break;
            }
        }
        return coordinates1;
    }

    private boolean isInIslandList(Coordinate coordinate, List<Island> islandList) {
        for (Island island : islandList) {
            if (island.hasCoordinate(coordinate)) {
                return true;
            }
        }
        return false;
    }

    private static <T> int getColumnCount(T[][] list) {
        return list[0].length;
    }

    private static <T> int getRowCount(T[][] list) {
        return list.length;
    }
}

public class Island {

    private Set<Coordinate> islandCoordinates = new ArraySet<>();

    public void addCoordinate(Coordinate coordinate) {
        islandCoordinates.add(coordinate);
    }

    public void addAllCoordinate(List<Coordinate> coordinates) {
        islandCoordinates.addAll(coordinates);
    }

    public boolean hasCoordinate(Coordinate coordinate) {
        for (Coordinate coordinate1 : islandCoordinates) {
            if (coordinate.equals(coordinate1)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (islandCoordinates.isEmpty()) {
            return "Empty island.";
        } else {
            String result = "Island has " + islandCoordinates.size() + " coordinates. \n\t";
            for (Coordinate coordinate : islandCoordinates) {
                result += "(" + String.valueOf(coordinate.x) + "," + String.valueOf(coordinate.y)
                        + "), ";
            }
            return result;
        }
    }
}