package filesprocessing;

import filesprocessing.filter.*;
import filesprocessing.order.*;

import java.io.File;
import java.io.*;
import java.util.*;

public class FileParser {
    private File commandFile;
    private int currentSectionLine = 0;
    private LinkedList<String[][]> sections = new LinkedList<>();
    private Iterator<String[][]> sectionsIterator;
    private String[][] rawInput = new String[NUM_OF_SUBSECTIONS+1][];

    private static final int NUM_OF_SUBSECTIONS = 2;
    private static final int NUM_OF_ORDER_ARGS = 2;
    private static final int NUM_OF_FILTER_ARGS = 3;
    private static final int FILTER = 0;
    private static final int ORDER = 1;
    private static final int LINE = 2;
    private static final int VALUE_INDEX = 1;
    private static final String DEFAULT_ORDER = "abs";
    private static final String NEXT_HEADER_TO_ORDER = "FILTER";
    private static final int BUFFER_SIZE = 100;
    private static final char INPUT_SEPARATOR = '#';

    private static final String ORDER_MISSING_ERR_MSG = "There is no order sub-section.";
    private static final String FILTER_MISSING_ERR_MSG = "There is no filter sub-section.";
    private static final String WARNING_MSG = "Warning in line ";
    private static final String ILLEGAL_SUBSECTION_NAME_MSG = " is an invalid sub-section name.";


    public FileParser(File commandFile) throws ErrorException, IOException {
        this.commandFile = commandFile;
        this.parse();
    }

    public void parse() throws ErrorException, java.io.IOException {
        try (LineNumberReader lnr = new LineNumberReader(new FileReader(commandFile))) {
            String header;
            while ((header = lnr.readLine()) != null) {
                lnr.mark(BUFFER_SIZE);
                String commandLine = lnr.readLine();
                switch (header) {
                    case "FILTER":
                        if (rawInput[FILTER] != null)
                            throw new IllegalFileFormatException(ORDER_MISSING_ERR_MSG);
                        rawInput[FILTER] = handleFilter(commandLine);
                        break;
                    case "ORDER":
                        if (rawInput[FILTER] == null)
                            throw new IllegalFileFormatException(FILTER_MISSING_ERR_MSG);
                        if (commandLine == null || commandLine.equals(NEXT_HEADER_TO_ORDER)) {
                            commandLine = DEFAULT_ORDER;
                            lnr.reset();
                        }
                        rawInput[ORDER] = handleOrder(commandLine);
                        break;
                    default:
                        throw new IllegalSubsectionNameException(header + ILLEGAL_SUBSECTION_NAME_MSG);
                }
                handleSection(rawInput, lnr.getLineNumber());
            }
            this.sectionsIterator = sections.iterator();
        }
    }

    private void handleSection(String[][] rawInput, int line) {
        if (rawInput[FILTER] != null && rawInput[ORDER] != null) {
            rawInput[LINE] = new String[]{""+line};
            this.sections.add(rawInput);
            this.rawInput = new String[NUM_OF_SUBSECTIONS+1][];
        }
    }

    private String[] handleOrder(String rawOrder) {
        String[] orderInput = new String[NUM_OF_ORDER_ARGS];
        int argIndex = 0;
        for (int i = 0; i < rawOrder.length(); i++) {
            if (rawOrder.charAt(i) == INPUT_SEPARATOR && argIndex < orderInput.length - 1) {
                argIndex++;
                continue;
            }
            if (orderInput[argIndex] == null) orderInput[argIndex] = "";
            orderInput[argIndex] += rawOrder.charAt(i);
        }
        return orderInput;
    }

    private String[] handleFilter(String raw) {
        String[] filterInput = new String[NUM_OF_FILTER_ARGS];
        filterInput[0] = "";
        int argIndex = 0;
        for (int i = 0; i < raw.length(); i++) {
            if (raw.charAt(i) == INPUT_SEPARATOR && argIndex < filterInput.length-1) {
                boolean isNextNum = (i+1 < raw.length() && raw.charAt(i + 1) <= '9' && raw.charAt(i + 1) >= '0');
                if (!isNextNum || argIndex != VALUE_INDEX) {
                    argIndex++;
                    continue;
                }
            }
            if (filterInput[argIndex] == null) filterInput[argIndex] = "";
            filterInput[argIndex] += raw.charAt(i);
        }
        return filterInput;
    }

    public Section generateSection(){
        if (!this.sectionsIterator.hasNext())
            return null;
        String[][] section = sectionsIterator.next();
        currentSectionLine=Integer.parseInt(section[LINE][0]);
        Filter filter = generateFilter(section[FILTER]);
        Order order = generateOrder(section[ORDER]);
        return new Section(filter, order);
    }

    private Filter generateFilter(String[] filterInput){
        Filter filter;
        try {
            filter = FilterFactory.createFilter(filterInput);
        } catch (WarningException e) {
            System.err.println(WARNING_MSG + (currentSectionLine - 2));
            filter = new All();
        }
        return filter;
    }

    private Order generateOrder(String[] orderInput){
        Order order;
        try {
            order = OrderFactory.createOrder(orderInput);
        } catch (WarningException e) {
            System.err.println(WARNING_MSG + (currentSectionLine));
            order = new Abs();
        }
        return order;
    }
}

