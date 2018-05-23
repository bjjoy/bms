package cn.bjjoy.bms.exception;

import cn.bjjoy.bms.base.Codes;
import cn.bjjoy.bms.base.ResponseResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author bjjoy
 * @date 2017/11/15
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = OperationException.class)
    public ResponseResult operationExceptionHandler(OperationException oe){
        LOGGER.error(oe.getData());
        LOGGER.error(oe.getMessage(), oe);
        return new ResponseResult(oe.getCode(), oe.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseResult noHandlerFoundException(NoHandlerFoundException e){
        LOGGER.error(e.getMessage(), e);
        return ResponseResult.error(Codes.SYSTEM_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseResult defaultExceptionHandler(Exception e){
        LOGGER.error(e.getMessage(),e);
        return ResponseResult.error(Codes.SYSTEM_ERROR);
    }
}
