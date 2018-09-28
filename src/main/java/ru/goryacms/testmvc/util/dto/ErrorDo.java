package ru.goryacms.testmvc.util.dto;

public class ErrorDo {
    private String code;

    private String message;

    public ErrorDo() {
    }

    public ErrorDo(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorDo{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}