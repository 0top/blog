package top.zerotop.global.exception;

public class ArticleException extends BlogException {
    public ArticleException() {
        super();
    }

    public ArticleException(String message) {
        super(message);
    }

    public ArticleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArticleException(Throwable cause) {
        super(cause);
    }
}
