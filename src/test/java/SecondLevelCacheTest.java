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
import java.util.List;

/**
 * @author Prongs
 * @date 2019/11/26 23:09
 */
public class SecondLevelCacheTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private UserDao userDao;
    private SqlSessionFactory factory;

    @Before//测试方法执行前执行
    public void init() throws Exception {
        //读取配置文件
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(inputStream);
        //使用工厂生产SqlSession对象        ↓自动提交事务
        sqlSession = factory.openSession(true);
        //使用SqlSession创建Dao接口代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After//测试方法执行后执行
    public void destroy() throws IOException {
        //执行提交事务
//        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void findOne() {
        SqlSession session = factory.openSession();
        UserDao userDao1 = session.getMapper(UserDao.class);
        User user = userDao1.findUserById(41);
        System.out.println(user);
        //释放一级缓存
        session.close();
        //再次打开session
        SqlSession sqlSession1 = factory.openSession();
        UserDao userDao2 = sqlSession1.getMapper(UserDao.class);
        User user1 = userDao2.findUserById(41);
        System.out.println(user1);

    }

}
