package thinking.cloud.rest.client.exception;

/**
 * Authorization未传入异常
 * <P>
 * 	在使用rest请求时, Authorization未传入则抛出此异常
 * </P>
 * @author think
 * @date 2020年11月4日
 */
public class AuthorizationIsNullException extends RuntimeException{

	private static final long serialVersionUID = -293130776190347890L;

	public AuthorizationIsNullException() {
		this("Authorization is null");
	}

	public AuthorizationIsNullException(String message) {
		super(message);
	}	
}
