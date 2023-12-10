package filesprocessing.filter;

public class All implements Filter {

    @Override
    public boolean isPass(java.io.File f) {
        return true;
    }
}
