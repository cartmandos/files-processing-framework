package filesprocessing.filter;

public class Prefix extends Value {
    Prefix(String value){
        super(value);
    }

    public boolean isPass(java.io.File f){
        return f.getName().startsWith(this.value);
    }
}
