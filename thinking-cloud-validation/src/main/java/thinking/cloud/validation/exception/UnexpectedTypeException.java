package thinking.cloud.validation.exception;

/**
 * 验证时出现意外的类型无法处理时，抛出此异常。
 * @author think
 */
public class UnexpectedTypeException extends RuntimeException {
	
	private static final long serialVersionUID = 3313704859808661424L;
	
	/**
	 * 验证时出现意外的类型无法处理时，抛出此异常
	 * @param message 异常信息
	 */
	public UnexpectedTypeException(String message) {
		super(message);
	}
}
