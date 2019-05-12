package top.zerotop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zerotop.utils.Result;

/**
 * Created by:zerotop  date:2019/5/12
 */
@ControllerAdvice
public class GlobalException {
    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler({BlogException.class})
    public @ResponseBody Result catchBlogException(BlogException e){
        logger.error("catch blog exception: {}", e.getMessage());
        return Result.error(500, e.getMessage());
    }

    @ExceptionHandler({UserAccountException.class})
    public @ResponseBody Result catchAccountException(UserAccountException e){
        logger.error("catch user account exception: {}", e.getMessage());
        return Result.error(500, e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public @ResponseBody Result unknowException(Exception e){

        e.printStackTrace();
        return Result.error(500, "请求出错...");
    }
}