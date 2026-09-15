//package com.tiamo.dt.dev.db.config;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
//import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
//import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
//import com.tiamo.dt.dev.db.handler.MybatisPlusMetaObjectHandler;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//
//@MapperScan("com.tiamo.dt.**.mapper")
//@Configuration
//public class MybatisPlusConfig {
//
//    /**
//     * 分页插件
//     */
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//
//        //分页插件
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//
//        //动态表名插件
//        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
//        HashMap<String, TableNameHandler> map = new HashMap<>();
//        map.put("res_pos_vel", (sql, tableName) -> tableName + "_" + DynamicTableNameHelper.getTableNameSuffix());
//        map.put("env_sat_risk", (sql, tableName) -> getTableName(tableName, DynamicTableNameHelper.getTableNameSuffix()));
//        map.put("env_goes_proton", (sql, tableName) -> getTableName(tableName, DynamicTableNameHelper.getTableNameSuffix()));
//        map.put("env_goes_electron", (sql, tableName) -> getTableName(tableName, DynamicTableNameHelper.getTableNameSuffix()));
//        map.put("env_goes_xray", (sql, tableName) -> getTableName(tableName, DynamicTableNameHelper.getTableNameSuffix()));
//        map.put("env_goes_geomagnetic", (sql, tableName) -> getTableName(tableName, DynamicTableNameHelper.getTableNameSuffix()));
//        map.put("env_poes_data", (sql, tableName) -> getTableName(tableName, DynamicTableNameHelper.getTableNameSuffix()));
//        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(map);
//        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);
//
//        return interceptor;
//    }
//
//    private String getTableName(String tableName, String suffix) {
//        return suffix != null ? tableName + suffix : tableName;
//    }
//
//    /**
//     * MybatisPlus 实体字段自动填充
//     * @return
//     */
//    @Bean
//    public MybatisPlusMetaObjectHandler mybatisPlusMetaObjectHandler() {
//        return new MybatisPlusMetaObjectHandler();
//    }
//}
