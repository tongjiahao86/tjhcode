package com.tjhnode.common.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class ErrorCode {

    public ErrorCode(){}

    //请求
    public String Code;

    //状态信息
    public String Message;

    public static ErrorCode Success() {
        ErrorCode errorCode=new ErrorCode();
        errorCode.Code="0";
        errorCode.Message="成功";
        return errorCode;
    }

    public static ErrorCode Unknown() {
        ErrorCode errorCode=new ErrorCode();
        errorCode.Code="-1";
        errorCode.Message="未知错误";
        return errorCode;
    }

    //成功状态
    public static ErrorCode Success;

}
