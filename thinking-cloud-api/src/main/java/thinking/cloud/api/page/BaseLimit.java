package thinking.cloud.api.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author admin
 * @date 2021/1/3 18:28
 */
@Data
public class BaseLimit implements Limit{

    private int pageNo = DEFAULT_PAGE_N0;

    private int pageSize = DEFAULT_PAGE_SIZE;

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo<=0 ? DEFAULT_PAGE_N0 : pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize<=0 ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
