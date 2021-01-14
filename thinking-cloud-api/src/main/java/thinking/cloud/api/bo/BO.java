package thinking.cloud.api.bo;

import java.io.Serializable;

/**
 * 业务BO的顶级抽象类
 * @author thinking
 * @date 2021/1/4 15:31
 */
public interface BO extends Serializable {
    public static interface insert{}
    public static interface delete{}
    public static interface update{}
    public static interface page{}
    public static interface select{}

}
