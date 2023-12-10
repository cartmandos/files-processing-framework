package filesprocessing.filter;

public class NegativeFilter implements Filter {
    protected Filter filterToNeg;

    NegativeFilter(Filter filter){
        this.filterToNeg = filter;
    }

    @Override
    public boolean isPass(java.io.File f) {
        return !this.filterToNeg.isPass(f);
    }
}
