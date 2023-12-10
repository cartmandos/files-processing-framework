package filesprocessing.order;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Abs implements Order, Comparator<File>{

    @Override
    public void sort(File[] filesList) {
        Arrays.sort(filesList, this);
    }

    @Override
    public int compare(File f1, File f2) {
        return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
    }
}
