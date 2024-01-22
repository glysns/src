package com.digytal.control.infra.business;

public class BusinessException extends  RuntimeException{
    private String errorCode;
    private String suggestion;
    private int httpStatus;
    public BusinessException(BusinessMessage error, Object ... params ){
        super(String.format(error.getMessage(), params));
        this.errorCode = error.getCode();
        this.suggestion = error.getSuggestion();
        this.httpStatus=error.getHttpStatus();
    }
    public BusinessException(String message){
        super(message);
        this.errorCode = "500";
        this.httpStatus=500;
        this.suggestion = "Contate o administrador";
    }
    public BusinessException(){
        this(BusinessMessage.E501);
    }
    public BusinessException(String errorCode, String suggestion,String message, Object ... params ){
        super(String.format(message, params));
        this.errorCode = errorCode;
        this.suggestion = suggestion;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
    public static String logMessage(BusinessException be){
        return String.format("#ALERTA de Validacao Regra Cod.: %s - Mensagem: %s - Sugestão: %s", be.getErrorCode(), be.getMessage(), be.getSuggestion());
    }
    public static String errorMessage(String mensagem, Object ... params){
        return "#ERRO: " + String.format(mensagem, params);
    }
}
