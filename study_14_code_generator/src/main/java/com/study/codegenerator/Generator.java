package com.study.codegenerator;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/03/05/14:55
 * @Description:
 */
public class Generator {
    /**
     * RUN THIS 代码生成模块测试
     */
    public static void main(String[] args) {
        GeneratorApi generator = new GeneratorApi();

        //数据库驱动名
        generator.setDriverName("com.mysql.cj.jdbc.Driver");
        //数据库链接地址
        generator.setUrl("jdbc:mysql://192.168.1.38:3307/aiptint?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false");
        //数据库用户名
        generator.setUsername("aiptint");
        //数据库密码
        generator.setPassword("Aiptint@123");
        //输出文件目录
        generator.setOutputDir("D:\\study-notes\\study_14_code_generator");
        //作者
        generator.setAuthor("ZJJ");
        //代码生成的包名
        generator.setPackageName("com.tiamo.code");
        //需要生成的表名(两者只能取其一)
        generator.setIncludeTables(new String[]{"mission_risk","mission_risk_rule"});
        //需要排除的表名(两者只能取其一)
        generator.setExcludeTables(new String[]{});
        generator.run();
    }
}
