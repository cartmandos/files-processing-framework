package filesprocessing.filter;

public class Executable extends Type {
    Executable(String answer) throws IllegalTypeArgumentException { super(answer); }

    @Override
    public boolean isPass(java.io.File f) {
        return isYes() == f.canExecute();
    }
}
