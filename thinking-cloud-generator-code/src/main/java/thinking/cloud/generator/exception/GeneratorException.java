package thinking.cloud.generator.exception;

/**
 * 
 * <P>
 * </P>
 * @author zhouxinke
 * @date 2021年3月8日
 */
public class GeneratorException extends RuntimeException {

	public GeneratorException() {
	}

	public GeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeneratorException(String message) {
		super(message);
	}

	public GeneratorException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -1101655112599303400L;
	
}
