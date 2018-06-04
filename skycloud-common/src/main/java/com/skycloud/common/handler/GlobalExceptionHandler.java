package com.skycloud.common.handler;

import com.skycloud.common.base.Result;
import com.skycloud.common.constants.CommonConstants;
import com.skycloud.common.exception.BaseException;
import com.skycloud.common.exception.auth.ClientTokenException;
import com.skycloud.common.exception.auth.UserInvalidException;
import com.skycloud.common.exception.auth.UserTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 */
@ControllerAdvice("com.skycloud")
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ClientTokenException.class)
    public Result clientTokenExceptionHandler(HttpServletResponse response, ClientTokenException ex) {
        response.setStatus(403);
        logger.error(ex.getMessage(), ex);
        return Result.getFailureResult(ex.getStatus() + "", ex.getMessage());
    }

    @ExceptionHandler(UserTokenException.class)
    public Result userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(200);
        logger.error(ex.getMessage(), ex);
        return Result.getFailureResult(ex.getStatus() + "", ex.getMessage());
    }

    @ExceptionHandler(UserInvalidException.class)
    public Result userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(200);
        logger.error(ex.getMessage(), ex);
        return Result.getFailureResult(ex.getStatus() + "", ex.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public Result baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(500);
        return Result.getFailureResult(ex.getStatus() + "", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        logger.error(ex.getMessage(), ex);
        return Result.getFailureResult(CommonConstants.EX_OTHER_CODE + "", ex.getMessage());
    }

}
