package com.tiamo.dt.db.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiamo.dt.common.utils.CommonUtil;
import com.tiamo.dt.db.pojo.page.PageParam;

/**
 * MybatisPlus工具类
 */
public class MPUtil {

    /**
     * 通过PageParam获取分页对象
     * @param pageParam
     * @return
     */
    public static Page getPage(PageParam pageParam){
        if(pageParam == null){
            pageParam = new PageParam();
        }

        Page page = new Page(pageParam.getPageCurrent(), pageParam.getPageSize());
        if(pageParam.getOrderBy() != null && !pageParam.getOrderBy().isEmpty()){
            String[] orderColumnArr = pageParam.getOrderBy().split(",");

            //驼峰转下划线，数据库中字段为下划线
            for (int i = 0; i < orderColumnArr.length; i++) {
                orderColumnArr[i] = CommonUtil.camelToUnderline(orderColumnArr[i]);
            }

            if(pageParam.getIsAsc()){
                page.addOrder(OrderItem.ascs(orderColumnArr));
            }else{
                page.addOrder(OrderItem.descs(orderColumnArr));
            }
        }
        return page;
    }

    /**
     * 获取QueryWrapper
     * @return
     */
    public static QueryWrapper getQueryWrapper(){
        return new QueryWrapper<>();
    }

    /**
     * 获取QueryWrapper
     * @param pageParam
     * @return
     */
    public static QueryWrapper getQueryWrapper(PageParam pageParam){
        if(pageParam == null){
            return new QueryWrapper<>();
        }

        QueryWrapper queryWrapper = new QueryWrapper<>();

        if(pageParam.getOrderBy() != null && !pageParam.getOrderBy().isEmpty()){
            String[] orderColumnArr = pageParam.getOrderBy().split(",");

            //驼峰转下划线，数据库中字段为下划线
            for (int i = 0; i < orderColumnArr.length; i++) {
                orderColumnArr[i] = CommonUtil.camelToUnderline(orderColumnArr[i]);
            }

            if(pageParam.getIsAsc()){
                queryWrapper.orderByAsc(orderColumnArr);
            }else{
                queryWrapper.orderByDesc(orderColumnArr);
            }
        }


        return queryWrapper;
    }

}
