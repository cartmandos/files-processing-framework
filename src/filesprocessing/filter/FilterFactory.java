package filesprocessing.filter;

public class FilterFactory {

    public static Filter createFilter(String[] filterInput) throws IllegalFilterNameException, IllegalSizeArgumentException, IllegalBetweenValueException, IllegalTypeArgumentException {
        String nameOfFilter = filterInput[0];
        String valueToCheck = filterInput[1];
        Filter filter;
        switch (nameOfFilter){
            case "greater_than":
                filter = new GreaterThan(Double.parseDouble(valueToCheck));
                break;
            case "between":
                double[] valuesArray = processBetweenValue(valueToCheck);
                filter = new Between(valuesArray[0], valuesArray[1]);
                break;
            case "smaller_than":
                filter = new SmallerThan(Double.parseDouble(valueToCheck));
                break;
            case "file":
                filter = new File(valueToCheck);
                break;
            case "contains":
                filter = new Contains(valueToCheck);
                break;
            case "prefix":
                filter = new Prefix(valueToCheck);
                break;
            case "suffix":
                filter = new Suffix(valueToCheck);
                break;
            case "writable":
                filter = new Writable(valueToCheck);
                break;
            case "executable":
                filter = new Executable(valueToCheck);
                break;
            case "hidden":
                filter = new Hidden(valueToCheck);
                break;
            case "all":
                filter = new All();
                break;
            default:
                throw new IllegalFilterNameException();
        }
        if (filterInput[2]!=null && filterInput[2].equals("NOT"))
            return new NegativeFilter(filter);
        return filter;
    }

    private static double[] processBetweenValue (String value) throws IllegalBetweenValueException {
        int indexOfHashTag = -1;
        for (int i=0; i<value.length() ; i++) {
            if (value.charAt(i) == '#') {
                indexOfHashTag = i;
                break;
            }
        }
        if (indexOfHashTag == -1)
            throw new IllegalBetweenValueException();
        double firstVal = Double.parseDouble(value.substring(0, indexOfHashTag));
        double lastVal = Double.parseDouble(value.substring(indexOfHashTag+1));
        return new double[]{firstVal, lastVal};
    }
}



