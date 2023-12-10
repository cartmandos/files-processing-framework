package filesprocessing.order;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Type implements Order, Comparator<File> {
    private static final char DELIMITER = '.';

    @Override
    public void sort(File[] filesList) {
        Arrays.sort(filesList, this);
    }

    @Override
    public int compare(File f1, File f2) {
        int typeComp = getExtension(f1.getName()).compareTo(getExtension(f2.getName()));
        if (typeComp == 0)
            return f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
        return typeComp;
    }

    private String getExtension(String s){
        int lastPeriod = s.lastIndexOf(DELIMITER);
        if (lastPeriod == -1 || lastPeriod == 0)
            return "";
        return s.substring(lastPeriod+1);
    }
}
