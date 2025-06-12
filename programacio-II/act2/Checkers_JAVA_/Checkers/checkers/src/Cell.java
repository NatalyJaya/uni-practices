public class Cell {

    private static final char C_FORBIDDEN = '·';
    private static final char C_EMPTY = ' ';
    private static final char C_WHITE = 'w';
    private static final char C_BLACK = 'b';

    public static final Cell FORBIDDEN = new Cell(C_FORBIDDEN);
    public static final Cell EMPTY = new Cell(C_EMPTY);
    public static final Cell WHITE = new Cell(C_WHITE);
    public static final Cell BLACK = new Cell (C_BLACK);

    private final char status;

    private Cell(char status) {
        //throw new UnsupportedOperationException("TODO: Step3");
        this.status = status;
    }

    public static Cell fromChar(char status) {
        //throw new UnsupportedOperationException("TODO: Step3");
       if (status == C_FORBIDDEN) return FORBIDDEN;
        else if ( status == C_EMPTY) return EMPTY;
        else if (status == C_WHITE) return  WHITE;
        else if (status == C_BLACK) return BLACK;
        else return null;

    }

    public boolean isForbidden() {
        //throw new UnsupportedOperationException("TODO: Step3");
        return this.status == C_FORBIDDEN;
    }

    public boolean isEmpty() {
        //throw new UnsupportedOperationException("TODO: Step3");
        return this.status == C_EMPTY;
    }

    public boolean isWhite() {
        //throw new UnsupportedOperationException("TODO: Step3");
        return this.status == C_WHITE;
    }

    public boolean isBlack() {
        //throw new UnsupportedOperationException("TODO: Step3");
        return this.status == C_BLACK;
    }

    @Override
    public String toString() {
        return String.valueOf(status);
    }
}