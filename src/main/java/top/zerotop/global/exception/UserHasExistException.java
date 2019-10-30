package top.zerotop.global.exception;

/**
 * Created by:zerotop  date:2019/10/30
 */
public class UserHasExistException extends BlogException {
    public UserHasExistException(String message) {
        super(message);
    }

    public UserHasExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserHasExistException(Throwable cause) {
        super(cause);
    }
}
