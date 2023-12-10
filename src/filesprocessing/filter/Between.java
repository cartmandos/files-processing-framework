package filesprocessing.filter;

public class Between extends Size{
    private double inMinSize;
    private double inMaxSize;

    Between(double inMinSize, double inMaxSize) throws IllegalBetweenValueException, IllegalSizeArgumentException {
        super(inMinSize, inMaxSize);
        if (!isLegalValues(inMinSize, inMaxSize)) throw new IllegalBetweenValueException();
        this.inMinSize = inMinSize;
        this.inMaxSize = inMaxSize;
    }

    private boolean isLegalValues(double minVal, double maxVal){
        return minVal <= maxVal;
    }

    @Override
    public boolean isPass(java.io.File f) {
        long fileSizeInBytes = f.length();
        return fileSizeInBytes >= inMinSize*BYTES_IN_KBYTES && fileSizeInBytes <= inMaxSize*BYTES_IN_KBYTES;
    }
}
