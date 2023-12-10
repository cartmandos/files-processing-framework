package filesprocessing;

abstract class FilesProcessingException extends Exception {
    FilesProcessingException(String s) {
        super(s);
    }

    FilesProcessingException() {
    }
}
