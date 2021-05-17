package thinking.cloud.beans.entity.abs;

/**
 * 业务BO的顶级抽象类
 * @author thinking
 * @date 2021/1/4 15:31
 */
public interface ValidationGroup{
	public static interface Insert{}
	public static interface Delete{}
	public static interface Update{}
	public static interface Select{}
	public static interface QueryPage{}
}
