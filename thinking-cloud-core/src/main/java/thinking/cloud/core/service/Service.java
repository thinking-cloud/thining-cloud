package thinking.cloud.core.service;

import thinking.cloud.api.page.Limit;
import thinking.cloud.api.page.Page;

import java.io.Serializable;

/**
 * 非数据库操作的service的顶级接口
 * <P>
 * 非数据库操作的service的顶级接口
 * </p>
 *
 * @param <T>  实体泛型
 * @author thinking
 */
public interface Service<T extends Serializable> {

    /**
     * 保存
     *
     * @param t 保存的实体
     * @return 操作是否成功，影响行数为0，则返回false。
     */
    default boolean insert(T t) {
        return false;
    }

    /**
     * 删除
     *
     * @param t 删除条件
     * @return 影响行数
     */
    default int delete(T t) {
        return -1;
    }



    /**
     * 修改
     *
     * @param t 保存的实体
     * @return 影响行数
     */
    default int update(T t) {
        return -1;
    }



    /**
     * 根据主键查询
     *
     * @param t 查询一个对象
     * @return 查询结果
     */
    default T selectByPK(T t) {
        return null;
    }

    /**
     * 分页查询
     *
     * @param limit 查询条件
     * @return 分页对象
     */
    default Page<T> queryPage(Limit limit) {
        return null;
    }

    /**
     * 查询总数
     *
     * @param limit 查询条件
     * @return 总数
     */
    default Long count(Limit limit) {
        return -1L;
    }
}
