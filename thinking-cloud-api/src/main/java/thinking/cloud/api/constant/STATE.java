package thinking.cloud.api.constant;

import thinking.cloud.api.exception.CodeNotFoundException;

public enum STATE {
	FALSE("00"),	// 假
	TRUE("01"),		// 真
	ENABLED("02"),	// 已启用
	DISABLED("03"),	// 已禁用
	DELETED("04"),	// 已删除
	ACTIVED("05"),	// 活跃的
	READ_ONLY("06"),	//只读的
	WRITE_ONLY("07");	//只写的


	private String code;
	private STATE(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}

	/**
	 * 将code直 转为 STATE
	 * @param code
	 * @return
	 */
	public static STATE codeOf(String code) {
		if(code == null) {
			throw new NullPointerException("code为null");
		}
		switch (code) {
			case "00":
				return STATE.FALSE;
			case "01":
				return STATE.TRUE;
			case "02":
				return STATE.ENABLED;
			case "03":
				return STATE.DISABLED;
			case "04":
				return STATE.DELETED;
			case "05":
				return STATE.ACTIVED;
			case "06":
				return STATE.READ_ONLY;
			case "07":
				return STATE.WRITE_ONLY;
			default:
				throw new CodeNotFoundException(code + " 不是STATE的实例");
		}
	}
}
