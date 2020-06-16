package thinking.cloud.validation.constants;

/**
 * 用于计算日期的枚举，确定计算单位
 * @author think
 */
public enum CalendarUnit {
	/** 年 */
	YEAR(5),
	/** 月 */
	MONTH(4),
	/** 日 */
	DAY(3),
	/** 小时 */
	HOUR(2),
	/** 分钟 */
	MINUTE(1),
	/** 秒 */
	SECOND(0);
	
	private int code=0;
	private CalendarUnit(int code) {
		this.code = code;
	}
	
	public int code() {
		return this.code;
	}
}
