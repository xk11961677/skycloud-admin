package com.skycloud.core.handler;

import com.skycloud.base.ResponseVo;
import com.skycloud.base.constant.CommonConstants;
import com.skycloud.base.exception.BussinessException;
import com.skycloud.base.exception.auth.ClientTokenException;
import com.skycloud.base.exception.auth.UserInvalidException;
import com.skycloud.base.exception.auth.UserTokenException;
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
    public ResponseVo clientTokenExceptionHandler(HttpServletResponse response, ClientTokenException ex) {
        response.setStatus(403);
        logger.error(ex.getMessage(), ex);
        return ResponseVo.error(ex.getStatus() + "", ex.getMessage());
    }

    @ExceptionHandler(UserTokenException.class)
    public ResponseVo userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        response.setStatus(200);
        logger.error(ex.getMessage(), ex);
        return ResponseVo.error(ex.getStatus() + "", ex.getMessage());
    }

    @ExceptionHandler(UserInvalidException.class)
    public ResponseVo userInvalidExceptionHandler(HttpServletResponse response, UserInvalidException ex) {
        response.setStatus(200);
        logger.error(ex.getMessage(), ex);
        return ResponseVo.error(ex.getStatus() + "", ex.getMessage());
    }

    @ExceptionHandler(BussinessException.class)
    public ResponseVo baseExceptionHandler(HttpServletResponse response, BussinessException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(500);
        return ResponseVo.error(ex.getStatus() + "", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseVo otherExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setStatus(500);
        logger.error(ex.getMessage(), ex);
        return ResponseVo.error(CommonConstants.EX_OTHER_CODE + "", ex.getMessage());
    }

}
