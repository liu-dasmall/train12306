package com.example.common.controller;

import com.example.common.exception.BusinessException;
import com.example.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 所有异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse exceptionHandler(Exception e) {
        CommonResponse CommonResponse = new CommonResponse();
        LOG.error("系统异常：", e);
        CommonResponse.setSuccess(false);
        CommonResponse.setMessage("系统出现异常，请联系管理员");
        return CommonResponse;
    }

    /**
     * 业务异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public CommonResponse exceptionHandler(BusinessException e) {
        CommonResponse CommonResponse = new CommonResponse();
        LOG.error("业务异常：{}", e.getE().getDesc());
        CommonResponse.setSuccess(false);
        CommonResponse.setMessage(e.getE().getDesc());
        return CommonResponse;
    }

    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResponse exceptionHandler(BindException e) {
        CommonResponse CommonResponse = new CommonResponse();
        LOG.error("校验异常：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        CommonResponse.setSuccess(false);
        CommonResponse.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return CommonResponse;
    }

}
