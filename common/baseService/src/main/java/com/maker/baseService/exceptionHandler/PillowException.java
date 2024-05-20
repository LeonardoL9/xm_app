package com.maker.baseService.exceptionHandler;

import lombok.Data;

@Data
public class PillowException extends RuntimeException{
    private Integer code;

    private String message;

    public PillowException(Integer code,String message){
        this.code=code;
        this.message=message;
    }

}
