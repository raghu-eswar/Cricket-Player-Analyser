package IPL;

public class PlayerAnalyserException extends Exception {
    enum ExceptionType {
        UNABLE_TO_PARSE, PROBLEM_IN_FIELDS, CSV_FILE_PROBLEM, EMPTY_FIELDS;
    }

    ExceptionType type;

    public PlayerAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    public PlayerAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public PlayerAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
