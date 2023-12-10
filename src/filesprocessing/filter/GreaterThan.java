package filesprocessing.filter;

public class GreaterThan extends Size {
    GreaterThan(double excMinSize) throws IllegalSizeArgumentException {
        super(excMinSize);
    }

    @Override
    public boolean isPass(java.io.File f) {
        return f.length() > givenSize*BYTES_IN_KBYTES;
    }
}
