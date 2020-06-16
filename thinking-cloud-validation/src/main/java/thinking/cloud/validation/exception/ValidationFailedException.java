package thinking.cloud.validation.exception;

/**
 * 验证失败时，抛出此异常
 * @author think
 *
 */
public class ValidationFailedException extends RuntimeException {

	private static final long serialVersionUID = -5166809200969926652L;

	public ValidationFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidationFailedException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * 验证失败时，抛出此异常
	 * @param message
	 */
	public ValidationFailedException(String message) {
		super(message);
	}
}
