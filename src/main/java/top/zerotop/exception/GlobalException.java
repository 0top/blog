package top.zerotop.exception;

import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.zerotop.utils.Result;

/**
 * Created by:zerotop  date:2019/5/12
 */
@ControllerAdvice
@RestController
public class GlobalException {
    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler({BlogException.class})
    public Result catchBlogException(BlogException e) {
        logger.error("catch blog exception: {}", e.getMessage());
        return Result.error(500, e.getMessage());
    }

    @ExceptionHandler({UserAccountException.class})
    public Result catchAccountException(UserAccountException e) {
        logger.error("catch user account exception: {}", e.getMessage());
        return Result.error(500, e.getMessage());
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public Result errorResult() {
        System.out.println("authenicate exception, please login...");
        return Result.error(50003, "请登录后重试");
    }

    @ExceptionHandler({Exception.class})
    public Result unknowException(Exception e) {
        e.printStackTrace();
        return Result.error(500, "系统响应,请求出错...");
    }
}
