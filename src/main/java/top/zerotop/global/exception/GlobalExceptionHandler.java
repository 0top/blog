package top.zerotop.global.exception;

import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import top.zerotop.utils.ServiceResult;

/**
 * Created by:zerotop  date:2019/5/12
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BlogException.class})
    public ServiceResult catchBlogException(BlogException e) {
        logger.error("catch blog exception: {}", e.getMessage());
        return ServiceResult.error(500, e.getMessage());
    }

    @ExceptionHandler({UserAccountException.class})
    public ServiceResult catchAccountException(UserAccountException e) {
        logger.error("catch user account exception: {}", e.getMessage());
        return ServiceResult.error(500, e.getMessage());
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ServiceResult errorResult() {
        logger.error("authenticate exception, please login...");
        return ServiceResult.error(50003, "请登录后重试");
    }

    @ExceptionHandler({Exception.class})
    public ServiceResult unknowException(Exception e) {
        e.printStackTrace();
        return ServiceResult.error(500, "系统响应,请求出错...");
    }
}
