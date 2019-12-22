package com.seljabali.practiceproblems.ui.chess

public class ChessBoard {
    public static final HEIGHT = 8;
    public static final WIDTH = HEIGHT;

    public ChessBoardItem boardOfItems[HEIGHT][WIDTH];

    public ChessBoard() {
        for () {
            for () {
                boardOfItems[i][j] = ChessBoardItem.emptyItem();
            }
        }
    }

    public void initalizeDefaultSetup() {

    }
}

// Game Type
//  Chess, Checkers, CH

// Game Config:
//  Player Count
//  ChessBoardItemsPerPlayer
//  ChessPieces
public class ChessBoardItemsPerPlayer {
    public Array<ChessPiece> chessPieces;
}

public class ChessBoardItem {
    private BordItemType bordItemType;
    private Player player;
    private ChessPiece chessPiece;

    private ChessBoardItem() {}

    public static ChessBoardItem emptyItem() {
        ChessBoardItem item = ChessBoardItem();
        item.boardItemType = NONE;
        return item;
    }

    public static ChessBoardItem newItem(Player player, ChessPiece chessPiece) {
        ChessBoardItem item = ChessBoardItem();
        item.bordItemType = BordItemType.PLAYER_ITEM;
        item.player = player;
        item.chessPiece = chessPiece;
        return item;
    }
}

public enum BordItemType {
    NONE, PLAYER_ITEM, NA;
}

public class Player {
    private String name;
    private PlayerType playerType;
}

public enum PlayerType {
    P1, P2;
}


public class ChessPiece {
    private String name;
    private Icon icon;
    public boolean getFeasiableMoves();
    public boolean getFeasiableEats();

    public interface CanMove {
        public boolean canMove(Array<ChessPieceMove> moves);
    }

    public interface CanEat {
        public boolean canEat(Array<ChessPieceMove> moves);
    }
}

public class Queen extends ChessPeice {

}

public class ChessPieceMove {
    private Direction direction;
    private int magnitude
}

/*
 * this always assumes first person perspective of direction
*/
enum Direction {
    NORTH, EAST, WEST, SOUTH, NORTH_WEST, ...
}


public class Icon {
    private String urlLocation;
}