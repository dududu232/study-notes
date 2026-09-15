package com.tiamo.dt.db.pojo.page;

import lombok.Data;

/**
 * 分页参数
 */
@Data
public class PageParam {

    private Integer pageCurrent = 1;

    private Integer pageSize = 10;

    private String orderBy;

    private Boolean isAsc = true;

}
