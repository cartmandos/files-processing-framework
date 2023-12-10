package filesprocessing;

class IllegalFileFormatException extends ErrorException {

    IllegalFileFormatException(String errMsg) {
        super(errMsg);
    }
}
