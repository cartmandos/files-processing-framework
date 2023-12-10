package filesprocessing.filter;

public class File extends Value {

    public File(String val) {
        super(val);
    }

    @Override
    public boolean isPass(java.io.File f) {
        return f.getName().equals(this.value);
    }
}
