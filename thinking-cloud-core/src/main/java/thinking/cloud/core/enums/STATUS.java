package thinking.cloud.core.enums;

import thinking.cloud.core.exception.CodeNotFoundException;

public enum STATUS {
	ENABLE("00"),
	DISABLE("01"),
	DELETE("02");
	
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
			throw new NullPointerException("code cannot be null");
		}
		switch (code) {
			case "00":
				return STATUS.ENABLE;
			case "01":
				return STATUS.DISABLE;
			case "02":
				return STATUS.DELETE;
			default:
				throw new CodeNotFoundException(code + " is not in the range of STATUS");
		}
	}
}
