package com.felix.config;

import com.felix.enums.RestCode;
import com.felix.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author felix
 * @description 全局异常处理
 * @date 2023/6/27 16:57
 */
@ControllerAdvice
public class ExceptionAspect {

    private static Logger log = LoggerFactory.getLogger(ExceptionAspect.class);

    @ResponseBody
    @ExceptionHandler(value = {BindException.class})
    public Result validateErrorHandler(Exception e) {
        log.error("paramError: ", e);
        String message = "";
        if(e instanceof BindException){
            BindException bindEx = (BindException) e;
            List<FieldError> fieldErrors = bindEx.getFieldErrors();
            message = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
        }
        return Result.fail(RestCode.PARAM_ERROR, message);
    }

    @ResponseBody
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public Result constraintErrorHandler(Exception e) {
        log.error("paramError: ", e);
        String message = "";
        if(e instanceof ConstraintViolationException){
            ConstraintViolationException ex = (ConstraintViolationException) e;
            message = ex.getMessage();
        }
        return Result.fail(RestCode.PARAM_ERROR, message);
    }

    @ResponseBody
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public Result paramErrorHandler(Exception e) {
        log.error("paramError: ", e);
        return Result.fail(RestCode.PARAM_ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {NullPointerException.class})
    public Result nullErrorHandler(Exception e) {
        log.error("systemErrorError: ", e);
        return Result.fail(RestCode.SYSTEM_ERROR, "空指针异常");
    }


    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public Result systemErrorHandler(Exception e) {
        log.error("systemError: ", e);
        return Result.fail(RestCode.SYSTEM_ERROR, "系统异常");
    }
}
