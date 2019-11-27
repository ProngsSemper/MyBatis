import com.prongs.dao.UserDao;
import com.prongs.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author Prongs
 * @date 2019/11/26 23:09
 */
public class MybatisTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before//测试方法执行前执行
    public void init() throws Exception {
        //读取配置文件
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        //使用工厂生产SqlSession对象
        sqlSession = factory.openSession();
        //使用SqlSession创建Dao接口代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After//测试方法执行后执行
    public void destroy() throws IOException {
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("saveTest");
        user.setAddress("广州番禺");
        user.setBirthday(new Date());
        user.setSex("男");

        //执行保存
        userDao.saveUser(user);

        //执行提交事务
        sqlSession.commit();

    }

    @Test
    public void testMyBatis() {
        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
