package filesprocessing.filter;

public class Suffix extends Value {
    Suffix(String val){
        super(val);
    }

    public boolean isPass(java.io.File f){
        return f.getName().endsWith(this.value);
    }
}
