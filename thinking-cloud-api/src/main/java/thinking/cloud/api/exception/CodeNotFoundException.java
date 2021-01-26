package thinking.cloud.api.exception;

/**
 * 字符串类型的code码转为enum对象时，若没有与code码对应的enum对象时，则抛出此异常
 * @author think
 *
 */
public class CodeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6895586959360697765L;

	public CodeNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CodeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeNotFoundException(String message) {
		super(message);
	}

	public CodeNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
