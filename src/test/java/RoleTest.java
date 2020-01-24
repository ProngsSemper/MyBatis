import com.prongs.dao.RoleDao;
import com.prongs.dao.UserDao;
import com.prongs.domain.Role;
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
public class RoleTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private RoleDao roleDao;

    @Before//测试方法执行前执行
    public void init() throws Exception {
        //读取配置文件
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        //使用工厂生产SqlSession对象        ↓自动提交事务
        sqlSession = factory.openSession(true);
        //使用SqlSession创建Dao接口代理对象
        roleDao = sqlSession.getMapper(RoleDao.class);
    }

    @After//测试方法执行后执行
    public void destroy() throws IOException {
        //执行提交事务
//        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void findAll(){
        List<Role> roles = roleDao.findAll();
        for (Role role : roles){
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }
    

}
