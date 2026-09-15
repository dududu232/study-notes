package com.tiamo.dt.db.config;

/**
 * 动态表名参数类
 */
public class DynamicTableNameHelper {

    /**
     * 动态表名参数保存
     */
    private static final ThreadLocal<String> TABLE_NAME_SUFFIX = new ThreadLocal<>();

    public static void setTableNameSuffix(String tableNameSuffix){
        TABLE_NAME_SUFFIX.set(tableNameSuffix);
    }

    public static String getTableNameSuffix() {
        return TABLE_NAME_SUFFIX.get();
    }
}
