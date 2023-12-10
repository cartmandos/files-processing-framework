package filesprocessing.filter;

public abstract class Size implements Filter {
    protected static final int BYTES_IN_KBYTES = 1024;
    protected double givenSize;

    public Size(double size1, double size2) throws IllegalSizeArgumentException {
        if (isNegativeNumber(size1) || isNegativeNumber(size2))
            throw new IllegalSizeArgumentException();
    }

    public Size(double size) throws IllegalSizeArgumentException {
        if (isNegativeNumber(size))
            throw new IllegalSizeArgumentException();
        this.givenSize = size;
    }

    private boolean isNegativeNumber(double size){ return size < 0; }

    public abstract boolean isPass(java.io.File f);

}
