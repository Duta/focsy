package focsy.compiler;

/**
 * Created by Bertie on 28/01/14.
 */
public class FileLocation {
    private static final int
            INITIAL_LINE = 1,
            INITIAL_COL = 1;
    private final int line, col;

    public FileLocation() {
        this(INITIAL_LINE, INITIAL_COL);
    }

    public FileLocation(int line, int col) {
        this.line = line;
        this.col = col;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

    public FileLocation nextLine() {
        return new FileLocation(line + 1, INITIAL_COL);
    }

    public FileLocation nextCol() {
        return new FileLocation(line, col + 1);
    }

    @Override
    public String toString() {
        return "FileLocation{" + line + ":" + col + "}";
    }
}
