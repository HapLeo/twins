package top.hapleow.twins.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.hapleow.twins.exception.BaseException;

/**
 * 全局异常处理器
 *
 * @author wuyulin
 * @date 2020/9/28
 */
@ControllerAdvice
@Data
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public Result handler(BaseException e) {

        return new Result(e.getMessage(), null);
    }

}
