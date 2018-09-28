package ru.goryacms.testmvc.util.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {

    private Object data;
    private ErrorDo meta;

    public ResponseDto(ErrorDo meta) {
        this.meta = meta;
    }

    public ResponseDto(Object obj) {
        this.data = obj;
    }

    public ResponseDto() {
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMeta(ErrorDo meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public ErrorDo getMeta() {
        return meta;
    }
}