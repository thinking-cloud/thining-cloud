package thinking.cloud.api.exception;

/**
 * 验证失败信息的异常
 * @author thinking
 * @date 2021/1/3 23:45
 */
public class ValidFailedException extends  RuntimeException{
    public ValidFailedException(String message) {
        super(message);
    }

    public ValidFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidFailedException(Throwable cause) {
        super(cause);
    }
}
