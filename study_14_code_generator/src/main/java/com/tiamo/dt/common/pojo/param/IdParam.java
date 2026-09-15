package com.tiamo.dt.common.pojo.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Controller接口，ID方法参数封装
 */
@Data
public class IdParam<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T id;
    private List<T> ids;
}
