package filesprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalArgumentException;

public class DirectoryProcessor {
    private static File sourceDir;
    private static File commandFile;

    private static final int SOURCE_DIR = 0;
    private static final int COMMAND_FILE = 1;
    private static final String ILLEGAL_ARGS_NUMBER = "Wrong number of arguments.";
    private static final String INCORRECT_ARGS = "First parameter should be source directory and the second a command file.";

    public static void main(String[] args) {
        try {
            setParam(args);
            File[] filesList = sourceDir.listFiles();
            if (filesList != null) {
                FileParser parser = new FileParser(commandFile);
                Section section;
                while ((section = parser.generateSection()) != null) {
                    File[] filteredList = filterList(filesList, section);
                    section.order(filteredList);
                    printFiles(filteredList);
                }
            }
    } catch (IllegalArgumentException | ErrorException | java.io.IOException e) {
            System.err.println("ERROR: " +e.getMessage() +"\n");
        }
    }

    private static void setParam(String[] args) throws IllegalArgumentException {
        if (args.length != 2)
            throw new IllegalArgumentException(ILLEGAL_ARGS_NUMBER);
        sourceDir = new File(args[SOURCE_DIR]);
        commandFile = new File(args[COMMAND_FILE]);
        if (!sourceDir.isDirectory() || !commandFile.isFile())
            throw new IllegalArgumentException(INCORRECT_ARGS);
    }

    private static File[] filterList(File[] filesList, Section section){
        List<File> tempList = new ArrayList<>();
        for (File f : filesList)
            if (f.isFile() && section.isPassFilter(f))
                tempList.add(f);
        return tempList.toArray(new File[tempList.size()]);
    }

    private static void printFiles(File[] filesList){
        for (File f : filesList)
            System.out.println(f.getName());
    }
}
