package filesprocessing.filter;

public class Hidden extends Type {
    Hidden(String answer) throws IllegalTypeArgumentException { super(answer); }

    public boolean isPass(java.io.File f){
        return isYes() == f.isHidden();
    }
}
