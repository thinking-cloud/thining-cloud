package thinking.cloud.core.exception;

/**
 * Bo类出现的异常
 * @author thinking
 * @date 2021/1/11 11:23
 */
public class BoException extends RuntimeException{
    public BoException() {
    }

    public BoException(String message) {
        super(message);
    }

    public BoException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoException(Throwable cause) {
        super(cause);
    }
}
