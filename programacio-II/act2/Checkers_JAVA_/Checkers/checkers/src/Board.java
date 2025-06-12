import java.util.StringTokenizer;

// Only a rectangle of cells. Does not know the game rules.

public class Board {

    private final int width;
    private final int height;
    private final Cell[][] cells;
    private int numBlacks;
    private int numWhites;

    public Board(int width, int height, String board) {
        //throw new UnsupportedOperationException("TODO: Step4");
        this.width = width;
        this.height = height;
        this.numBlacks = 0;
        this.numWhites = 0;
        cells = new Cell[height][width];

        structureBoard(board);
    }

    private void structureBoard(String board) {
        StringTokenizer token = new StringTokenizer(board,"\n");
        int j =0;

        while (token.hasMoreTokens()) {
            String item = token.nextToken();
            for (int i = 0; i < width; i++) {
                cells[j][i] = Cell.fromChar(item.charAt(i));

                if(Cell.fromChar(item.charAt(i)) == Cell.WHITE) {
                    numWhites++;
                } else if (Cell.fromChar(item.charAt(i)) == Cell.BLACK) {
                    numBlacks++;
                }
            }
            j++;
        }
    }

    public int getWidth() {
        //throw new UnsupportedOperationException("TODO: Step4");
        return this.width;
    }

    public int getHeight() {
        //throw new UnsupportedOperationException("TODO: Step4");
        return this.height;
    }

    public int getNumBlacks() {
        //throw new UnsupportedOperationException("TODO: Step4");
        return this.numBlacks;
    }

    public int getNumWhites() {
        //throw new UnsupportedOperationException("TODO: Step4");
        return this.numWhites;
    }

    public boolean isForbidden(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        int posX = pos.getX();
        int posY = pos.getY();

        boolean outOfBounds = (posX < 0 || posX >= getWidth() || posY < 0 || posY >= getHeight());

        boolean cellForbidden = !outOfBounds && cells[posY][posX].isForbidden();

        return outOfBounds || cellForbidden;
    }

    public boolean isBlack(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        return (!isForbidden(pos)) && cells[pos.getY()][pos.getX()].isBlack();
    }

    public boolean isWhite(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        return (!isForbidden(pos)) && cells[pos.getY()][pos.getX()].isWhite();
    }

    public boolean isEmpty(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        return (!isForbidden(pos)) && cells[pos.getY()][pos.getX()].isEmpty();
    }
    private void positionEdited(Position pos){
        if(isWhite(pos))numWhites--;
        else if(isBlack(pos))numBlacks--;
    }

    public void setBlack(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        positionEdited(pos);
        cells[pos.getY()][pos.getX()] = Cell.BLACK;
        numBlacks++;
    }

    public void setWhite(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        positionEdited(pos);
        cells[pos.getY()][pos.getX()] = Cell.WHITE;
        numWhites++;
    }

    public void setEmpty(Position pos) {
        //throw new UnsupportedOperationException("TODO: Step4");
        positionEdited(pos);
        cells[pos.getY()][pos.getX()] = Cell.EMPTY;
    }


    // For testing and debugging

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(cells[y][x].toString());
            }
            if (y != height - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
