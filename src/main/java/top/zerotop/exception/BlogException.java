package top.zerotop.exception;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年8月24日下午10:20:43
 */
public class BlogException extends Exception {
    public BlogException() {
        super();
    }

    public BlogException(String message) {
        super(message);
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogException(Throwable cause) {
        super(cause);
    }
}
