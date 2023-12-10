package filesprocessing.filter;

public class SmallerThan extends Size {

    SmallerThan(double excMaxSize) throws IllegalSizeArgumentException {
        super(excMaxSize);
    }

    @Override
    public boolean isPass(java.io.File f) {
        return f.length() < givenSize*BYTES_IN_KBYTES;    }
}
