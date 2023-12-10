package filesprocessing.filter;

public class Contains extends Value {
    Contains(String val){
        super(val);
    }

    @Override
    public boolean isPass(java.io.File f) {
        return f.getName().contains(this.value);
    }
}
