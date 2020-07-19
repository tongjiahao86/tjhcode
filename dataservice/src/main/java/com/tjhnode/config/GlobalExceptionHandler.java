package com.tjhnode.config;

import com.tjhnode.common.vo.ErrorCode;
import com.tjhnode.common.vo.ErrorCodeManager;
import com.tjhnode.common.vo.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @program: dataservice
 * @description: 统一异常处理
 * @author: tjh
 * @create: 2020-07-18 21:43
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private ErrorCode errorCode= ErrorCodeManager.GetCode("99");

    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public JSONResult runtimeExceptionHandler(RuntimeException ex) {
        return new JSONResult(errorCode,"运行时异常:"+ex.getMessage());
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public JSONResult nullPointerExceptionHandler(NullPointerException ex) {
        System.err.println("NullPointerException:");
        return new JSONResult(errorCode,"空指针异常:"+ex.getMessage());
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public JSONResult classCastExceptionHandler(ClassCastException ex) {
        return new JSONResult(errorCode,"类型转换异常:"+ex.getMessage());
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public JSONResult iOExceptionHandler(IOException ex) {
        return new JSONResult(errorCode,"IO异常:"+ex.getMessage());
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public JSONResult noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return new JSONResult(errorCode,"未知方法异常:"+ex.getMessage());
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public JSONResult indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return new JSONResult(errorCode,"数组越界异常:"+ex.getMessage());
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public JSONResult requestNotReadable(HttpMessageNotReadableException ex) {
        System.out.println("400..requestNotReadable");
        return new JSONResult(errorCode,"400..requestNotReadable;"+ex.getMessage());
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public JSONResult requestTypeMismatch(TypeMismatchException ex) {
        System.out.println("400..TypeMismatchException");
        return new JSONResult(errorCode,"400..TypeMismatchException;"+ex.getMessage());
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public JSONResult requestMissingServletRequest(MissingServletRequestParameterException ex) {
        System.out.println("400..MissingServletRequest");
        return new JSONResult(errorCode,"400..MissingServletRequest;"+ex.getMessage());
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public JSONResult request405(HttpRequestMethodNotSupportedException ex) {
        return new JSONResult(errorCode,"405错误:"+ex.getMessage());
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public JSONResult request406(HttpMediaTypeNotAcceptableException ex) {
        System.out.println("406...");
        return new JSONResult(errorCode,"406错误:"+ex.getMessage());
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public JSONResult server500(RuntimeException ex) {
        System.out.println("500...");
        return new JSONResult(errorCode,"500错误:"+ex.getMessage());
    }

    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public JSONResult requestStackOverflow(StackOverflowError ex) {
        return new JSONResult(errorCode,"栈溢出:"+ex.getMessage());
    }

    //除数不能为0
    @ExceptionHandler({ArithmeticException.class})
    public JSONResult arithmeticException(ArithmeticException ex) {
        return new JSONResult(errorCode,"除数不能为0:"+ex.getMessage());
    }


    //其他错误
    @ExceptionHandler({Exception.class})
    public JSONResult exception(Exception ex) {
        return new JSONResult(errorCode,ex.getMessage());
    }

    private <T extends Throwable> JSONResult resultFormat(Integer code, T ex) {
        ex.printStackTrace();
        log.error(String.format(logExceptionFormat, code, ex.getMessage()));
        return new JSONResult(errorCode, ex.getMessage());
    }

}
