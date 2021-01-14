package thinking.cloud.api.constant;

public enum MESSAGE {
	SUCCESS("00"),
	FAILURE("01"),
	ERROR("02");
	
	private String code;
	private MESSAGE(String code) {
		this.code = code;
	}
	
	public String code() {
		return this.code;
	}
	
}
