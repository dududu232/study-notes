package com.study.codegenerator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/03/05/14:55
 * @Description:
 */
@Data
public class GeneratorApi {

    /**
     * 数据库驱动名
     */
    private String driverName;
    /**
     * 数据库链接地址
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String username;
    /**
     * 数据库密码
     */
    private String password;
    /**
     * 输出文件目录
     */
    private String outputDir;
    /**
     * 作者
     */
    private String author = "Tiamo";
    /**
     * 代码生成的包名
     */
    private String packageName;
    /**
     * 需要生成的表名(两者只能取其一)
     */
    private String[] includeTables;
    /**
     * 需要排除的表名(两者只能取其一)
     */
    private String[] excludeTables;
    /**
     * 是否启用swagger
     */
    private Boolean isSwagger2 = Boolean.TRUE;

    public void run() {
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        //代码输出目录
        gc.setOutputDir(outputDir + "/src/main/java");
        //开发人员
        gc.setAuthor(author);

        gc.setFileOverride(true);
        //是否打开输出目录
        gc.setOpen(false);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        //Mybatis Mapping.xml中增加BaseResult和BaseColumn
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
		/*gc.setMapperName("%sMapper");
		gc.setXmlName("%sMapping");
		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");*/
        gc.setSwagger2(isSwagger2);
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(this.driverName);
        dsc.setUrl(this.url);
        dsc.setUsername(this.username);
        dsc.setPassword(this.password);
        if (this.driverName.contains(DbType.MYSQL.getDb())) {
            dsc.setDbType(DbType.MYSQL);
            dsc.setTypeConvert(new MySqlTypeConvert());
        } else if (this.driverName.contains(DbType.POSTGRE_SQL.getDb())) {
            dsc.setDbType(DbType.POSTGRE_SQL);
            dsc.setTypeConvert(new PostgreSqlTypeConvert());
        } else if (this.driverName.contains(DbType.SQL_SERVER.getDb())) {
            dsc.setDbType(DbType.SQL_SERVER);
            dsc.setTypeConvert(new SqlServerTypeConvert());
        } else if (this.driverName.contains(DbType.ORACLE.getDb())) {
            dsc.setDbType(DbType.ORACLE);
            dsc.setTypeConvert(new OracleTypeConvert());
        } else {
            System.out.println("数据库类型异常，代码生成失败！");
            return;
        }
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名
        // strategy.setDbColumnUnderline(true);//全局下划线命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//		strategy.setTablePrefix(tablePrefix);
        if (includeTables.length > 0) {
            strategy.setInclude(includeTables);
        }
        if (excludeTables.length > 0) {
            strategy.setExclude(excludeTables);
        }

        // 自定义 controller 父类
//		strategy.setSuperControllerClass("com.tiamo.dev.db.controller.BaseController");
        strategy.setEntityBuilderModel(false);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 控制台扫描
        pc.setModuleName(null);
        //父包名
        pc.setParent(packageName);
        //controller包名
        pc.setController("controller");
        //entity包名
        pc.setEntity("entity");
        //mapping包名
        pc.setXml("mapping");
        mpg.setPackageInfo(pc);

        //自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };

        //如果模板引擎是 freemarker
        //String templatePath = "/templates/mapping.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapping.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return outputDir + "/src/main/resources/mapping/"
                        + tableInfo.getEntityName() + "Mapping" + StringPool.DOT_XML;
            }
        });

        injectionConfig.setFileOutConfigList(focList);
        mpg.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        //templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        mpg.execute();
    }
}
