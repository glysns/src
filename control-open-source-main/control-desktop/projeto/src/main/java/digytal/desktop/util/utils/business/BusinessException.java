package digytal.desktop.util.utils.business;

public class BusinessException extends  RuntimeException{
    private String errorCode;
    private String suggestion;
    public BusinessException(String errorCode, String message, String suggestion){
        super(message);
        this.errorCode = errorCode;
        this.suggestion = suggestion;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getSuggestion() {
        return suggestion;
    }

}
