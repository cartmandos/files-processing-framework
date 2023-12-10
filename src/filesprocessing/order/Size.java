package filesprocessing.order;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Size implements Order, Comparator<File> {
    public Size(){}

    @Override
    public void sort(File[] filesList) {
        Arrays.sort(filesList, this);
    }

    @Override
    public int compare(File f1, File f2) {
        int sizeComp = Long.compare(f1.length(), f2.length());
        if (sizeComp == 0)
            return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
        return sizeComp;
    }
}
