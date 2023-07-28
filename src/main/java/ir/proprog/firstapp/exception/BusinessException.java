package ir.proprog.firstapp.exception;

public class BusinessException extends Exception {
    private String message;
    private String errorCode;
    private Object causeObject;

    public BusinessException(String message, String errorCode, Object causeObject) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.causeObject = causeObject;
    }
}
