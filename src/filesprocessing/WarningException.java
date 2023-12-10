package filesprocessing;

/**
 * An exception representing a Type I error - runtime errors that the file processing
 * can recover from and prints a warning.
 */
public abstract class WarningException extends FilesProcessingException {
    public WarningException(){
        super();
    }
}
