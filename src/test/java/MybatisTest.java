import com.prongs.dao.UserDao;
import com.prongs.domain.QueryVo;
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
        //执行提交事务
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("last inserted id");
        user.setUserAddress("广州番禺");
        user.setUserBirthday(new Date());
        user.setUserSex("女");
        System.out.println("保存之前id:" + user.getUserId());
        //执行保存
        userDao.saveUser(user);
        System.out.println("保存之后id:" + user.getUserId());
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setUserId(41);
        user.setUserName("saveUpdate");
        user.setUserAddress("广州番禺");
        user.setUserBirthday(new Date());
        user.setUserSex("女");
        //执行更新
        userDao.updateUser(user);

    }

    @Test
    public void testFindAll() {
        //使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testDelete() {
        userDao.deleteUser(41);
    }

    @Test
    public void testFindById() {
        User user = userDao.findUserById(43);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
        List<User> users = userDao.findUserByName("王");
        System.out.println(users);
    }

    @Test
    public void testTotal() {
        System.out.println(userDao.total());
    }

    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
