import acm.graphics.GDimension;
import acm.graphics.GPoint;

public class Geometry {

    private final int windowWidth;
    private final int windowHeight;
    private final int numCols;
    private final int numRows;
    private final double boardPadding;
    private final double cellPadding;

    public Geometry(int windowWidth, int windowHeight, int numCols, int numRows, double boardPadding, double cellPadding) {
       // throw new UnsupportedOperationException("TODO: Step6");
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        this.numCols = numCols;
        this.numRows = numRows;
        this.boardPadding = boardPadding;
        this.cellPadding = cellPadding;
    }

    public int getRows() {
        //throw new UnsupportedOperationException("TODO: Step6");
        return this.numRows;
    }

    public int getColumns() {
        //throw new UnsupportedOperationException("TODO: Step6");
        return this.numCols;
    }

    public GDimension boardDimension() {
        //throw new UnsupportedOperationException("TODO: Step6");
        return new GDimension(
                this.windowWidth - (this.windowWidth * this.boardPadding * 2),
                this.windowHeight - (this.windowHeight * this.boardPadding * 2));
    }

    public GPoint boardTopLeft() {
        //throw new UnsupportedOperationException("TODO: Step6");
        return new GPoint(
                this.windowWidth * this.boardPadding,
                this.windowHeight * this.boardPadding);
    }

    public GDimension cellDimension() {
        //throw new UnsupportedOperationException("TODO: Step6");
        return new GDimension(
                boardDimension().getWidth() / this.numCols,
                boardDimension().getHeight() / this.numRows);
    }

    public GPoint cellTopLeft(int x, int y) {
        //throw new UnsupportedOperationException("TODO: Step6");
        return  new GPoint(
                this.boardTopLeft().getX() + x * cellDimension().getWidth(),
                this.boardTopLeft().getY() + y * (cellDimension().getHeight()));
    }

    public GDimension tokenDimension() {
       // throw new UnsupportedOperationException("TODO: Step6");
        return new GDimension(
                cellDimension().getWidth() - (cellDimension().getWidth() * this.cellPadding * 2),
                cellDimension().getHeight() - (cellDimension().getHeight() * this.cellPadding * 2));
    }

    public GPoint tokenTopLeft(int x, int y) {
        //throw new UnsupportedOperationException("TODO: Step6");
        return new GPoint(
                cellTopLeft(x,y).getX() + (cellDimension().getWidth() - tokenDimension().getWidth()) / 2,
                cellTopLeft(x,y).getY() + (cellDimension().getHeight() - tokenDimension().getHeight()) / 2);
    }

    public GPoint centerAt(int x, int y) {
        //throw new UnsupportedOperationException("TODO: Step6");
        return new GPoint(
                cellTopLeft(x,y).getX() + Math.floorDiv((int) cellDimension().getWidth(), 2),
                cellTopLeft(x,y).getY() + Math.floorDiv((int)cellDimension().getHeight(), 2));
    }

    public Position xyToCell(double x, double y) {
        GPoint boardTopLeft = boardTopLeft();
        GDimension cellDimension = cellDimension();
        return new Position(
                (int) ((x - boardTopLeft.getX()) / cellDimension.getWidth()),
                (int) ((y - boardTopLeft.getY()) / cellDimension.getHeight()));
    }
}
