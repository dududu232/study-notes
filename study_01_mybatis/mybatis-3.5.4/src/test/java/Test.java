import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.submitted.permissions.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test {
  public static void main(String[] args) throws IOException {
    //第一步：读取mybatis-config.xml配置文件
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");


   // InputStream fileInputStream = new FileInputStream(new File("/mybatis-config.xml"));
//第二步：构建SqlSessionFactory  这里使用的是构建器模式
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    System.out.println("aa");

//第三步：打开SqlSession
    SqlSession session = sqlSessionFactory.openSession();

//第四步：获取Mapper接口对象
  //UUserInfoMapper uUserInfoMapper = session.getMapper(UUserInfoMapper.class);

//第五步：调用Mapper接口对象的方法操作数据库；
    /*  UUserInfo uUserInfo = uUserInfoMapper.selectByPrimaryKey(1);

    //第六步：业务处理
    log.info("查询结果: " + uUserInfo.getId() + "--" + uUserInfo.getPhone());*/
  }
}
