package focsy.compiler;

/**
 * Created by Bertie on 28/01/14.
 */
public class FileRange {
    private final FileLocation start, end;

    public FileRange(FileLocation point) {
        this(point, point);
    }

    public FileRange(FileLocation start, FileLocation end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return start + "-" + end;
    }

    public FileLocation getStart() {
        return start;
    }

    public FileLocation getEnd() {
        return end;
    }
}
