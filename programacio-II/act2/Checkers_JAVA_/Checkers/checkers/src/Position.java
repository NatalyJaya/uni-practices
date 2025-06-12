public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        //throw new UnsupportedOperationException("TODO: Step1");
        this.x = x;
        this.y = y;
    }

    public int getX() {
        //throw new UnsupportedOperationException("TODO: Step1");
        return this.x;
    }

    public int getY() {
        //throw new UnsupportedOperationException("TODO: Step1");
        return this.y;
    }

    public boolean sameDiagonalAs(Position other) {
        //throw new UnsupportedOperationException("TODO: Step1");
       return(this.x + this.y == other.getX() + other.getY() || this.x - this.y == other.getX() - other.getY());

    }

    public static int distance(Position pos1, Position pos2) {
        //throw new UnsupportedOperationException("TODO: Step1");
    return Math.abs(pos1.getX()- pos2.getX()) + Math.abs(pos1.getY()- pos2.getY());
    }

    public static Position middle(Position pos1, Position pos2) {
        //throw new UnsupportedOperationException("TODO: Step1");
        return new Position(Math.floorDiv(pos1.x + pos2.x, 2), Math.floorDiv(pos1.y + pos2.y, 2));
    }

    // Needed for testing and debugging

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

