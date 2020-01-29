import com.prongs.dao.AccountDao;
import com.prongs.domain.Account;
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
public class AccountTest {
    private InputStream inputStream;
    private SqlSession sqlSession;
    private AccountDao accountDao;

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
        accountDao = sqlSession.getMapper(AccountDao.class);
    }

    @After//测试方法执行后执行
    public void destroy() throws IOException {
        //执行提交事务
//        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void findAll() {
        List<Account> list = accountDao.findAll();
        for (Account account : list) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }



}
