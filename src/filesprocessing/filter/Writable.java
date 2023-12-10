package filesprocessing.filter;

public class Writable extends Type {
    Writable(String ans) throws IllegalTypeArgumentException { super(ans); }

    public boolean isPass(java.io.File f){
        return isYes() == f.canWrite();
    }
}
