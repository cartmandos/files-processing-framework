package filesprocessing.filter;

public abstract class Value implements Filter {
    protected String value;

    public Value(String val){
        this.value = val;
    }

    public abstract boolean isPass(java.io.File f);

}
