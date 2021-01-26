package thinking.cloud.api.constant;

import thinking.cloud.api.exception.CodeNotFoundException;

public enum STATUS {
	FALSE("00"),
	TRUE("01"),
	ENABLE("02"),
	DISABLE("03"),
	DELETE("04");


	private String code;
	private STATUS(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}

	/**
	 * 将code直 转为 STATUS
	 * @param code
	 * @return
	 */
	public static STATUS codeOf(String code) {
		if(code == null) {
			throw new NullPointerException("code为null");
		}
		switch (code) {
			case "00":
				return STATUS.FALSE;
			case "01":
				return STATUS.TRUE;
			case "02":
				return STATUS.ENABLE;
			case "03":
				return STATUS.DISABLE;
			case "04":
				return STATUS.DELETE;
			default:
				throw new CodeNotFoundException(code + " 不是STATUS的实例");
		}
	}
}
