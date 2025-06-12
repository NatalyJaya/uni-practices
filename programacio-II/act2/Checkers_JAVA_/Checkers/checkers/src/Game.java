import java.util.ArrayList;
import java.util.List;

public class Game {

    private static final Direction[] WHITE_DIRECTIONS = {Direction.NW, Direction.NE};
    private static final Direction[] BLACK_DIRECTIONS = {Direction.SW, Direction.SE};

    private final Board board;
    private Player currentPlayer;
    private boolean hasWon;

    public Game(Board board) {
        //throw new UnsupportedOperationException("TODO: Step5");
        this.board = board;
        this.currentPlayer = Player.WHITE;
        this.hasWon = false;
    }

    public Player getCurrentPlayer() {
        //throw new UnsupportedOperationException("TODO: Step5");
        return this.currentPlayer;
    }

    public boolean hasWon() {
        //throw new UnsupportedOperationException("TODO: Step5");
        return this.hasWon;
    }

    public boolean isValidFrom(Position position) {
        //throw new UnsupportedOperationException("TODO: Step5");
        Direction[] validDirections = (getCurrentPlayer() == Player.WHITE) ? WHITE_DIRECTIONS : BLACK_DIRECTIONS;

        if (board.isEmpty(position) || board.isForbidden(position)) return false;

        if (isOpponentPiece(position)) return false;

        return isValidPosition(position, validDirections);
    }

    private boolean isValidPosition(Position position, Direction[] validDirections) {
        for (Direction direction : validDirections) {
            Position nextPosition = direction.apply(position);

            if (nextPosition != null) {
                if (isOpponentPiece(nextPosition)) {
                    Position enemy = direction.apply(nextPosition);

                    if (board.isEmpty(enemy) && position.sameDiagonalAs(enemy)) {
                        return true;
                    }
                } else if (board.isEmpty(nextPosition)) return true;

            }
        }
        return false;
    }

    private boolean isOpponentPiece(Position position) {
        return (getCurrentPlayer() == Player.WHITE) ? board.isBlack(position) : board.isWhite(position);
    }

    public boolean isValidTo(Position validFrom, Position to) {
        //throw new UnsupportedOperationException("TODO: Step5");
        boolean isWhite = getCurrentPlayer() == Player.WHITE;

        Position mid = Position.middle(validFrom, to);
        int dist = Position.distance(validFrom, to);

        if (!this.board.isEmpty(to) ||
                !validFrom.sameDiagonalAs(to) ||
                !(isWhite ? to.getY() <= validFrom.getY() : to.getY() >= validFrom.getY())) {
            return false;
        }

        return dist == 2 || (dist == 4 && (isWhite ? this.board.isBlack(mid) : this.board.isWhite(mid)));
    }


    public Move move(Position validFrom, Position validTo) {
        // throw new UnsupportedOperationException("TODO: Step5");
        Position mid = null;

        if (Position.distance(validFrom, validTo) > 2) {
            mid = Position.middle(validFrom, validTo);
            board.setEmpty(mid);
        }

        movePiece(validFrom,validTo);

        updateWonStatus();

        return new Move(validFrom,mid,validTo);
    }
    private void movePiece(Position validFrom, Position validTo) {
        if (board.isWhite(validFrom)) board.setWhite(validTo);
        else board.setBlack(validTo);
        board.setEmpty(validFrom);
    }
    private void updateWonStatus() {
        this.hasWon = hasPlayerWon(getCurrentPlayer() == Player.WHITE);
        if (!hasWon)  currentPlayer = (getCurrentPlayer() == Player.WHITE) ? Player.BLACK : Player.WHITE;

    }

    private boolean hasPlayerWon(boolean isWhite) {
        int startY = isWhite ? 0 : board.getHeight() - 1;
        int opponentPieces = isWhite ? board.getNumBlacks() : board.getNumWhites();

        if (opponentPieces == 0) return true;

        for (int x = 0; x < board.getWidth(); x++) {
            Position pos = new Position(x, startY);
            if ((isWhite && board.isWhite(pos)) || (!isWhite && board.isBlack(pos))) return true;
        }

        return playerHasNoMoves(!isWhite);
    }

    private boolean playerHasNoMoves(boolean isWhite) {
        Position pos;
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                pos = new Position(x, y);

                if ((isWhite && board.isWhite(pos)) || (!isWhite && board.isBlack(pos))) {
                    if (!canMove(pos)) return false;

                }
            }
        }
        return true;
    }

    private boolean canMove(Position pos) {
        if (board.isWhite(pos)) {
            return board.isEmpty(WHITE_DIRECTIONS[0].apply(pos))
                    || board.isEmpty(WHITE_DIRECTIONS[1].apply(pos))
                    || (board.isBlack(WHITE_DIRECTIONS[0].apply(pos)) && board.isEmpty(WHITE_DIRECTIONS[0].apply(WHITE_DIRECTIONS[0].apply(pos))))
                    || (board.isBlack(WHITE_DIRECTIONS[1].apply(pos)) && board.isEmpty(WHITE_DIRECTIONS[1].apply(WHITE_DIRECTIONS[1].apply(pos))));
        } else if (board.isBlack(pos)) {
            return board.isEmpty(BLACK_DIRECTIONS[0].apply(pos))
                    || board.isEmpty(BLACK_DIRECTIONS[1].apply(pos))
                    || (board.isWhite(BLACK_DIRECTIONS[0].apply(pos)) && board.isEmpty(BLACK_DIRECTIONS[0].apply(BLACK_DIRECTIONS[0].apply(pos))))
                    || (board.isWhite(BLACK_DIRECTIONS[1].apply(pos)) && board.isEmpty(BLACK_DIRECTIONS[1].apply(BLACK_DIRECTIONS[1].apply(pos))));
        }
        return false;
    }



    // Only for testing

    public void setPlayerForTest(Player player) {
        this.currentPlayer = player;
    }
}
