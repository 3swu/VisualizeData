import com.wulei.Beans.User;
import com.wulei.DAO.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-dao.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsertUser() throws Exception{
        User user = new User();
        user.setUsername("1046828876@qq.com");
        user.setPassword("123456");
        user.setRegtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(userMapper.insertUser(user));
    }

    @Test
    public void testGetUserById() throws Exception{
        System.out.println(userMapper.getUserById(1));
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        System.out.println(userMapper.getUserByUsername("1046828876@qq.com"));
    }

    @Test
    public void testGetUserByUsernameAndPassword() throws Exception {
        User user = new User();
        user.setUsername("1046828876@qq.com");
        user.setPassword("123456");
        System.out.println(userMapper.getUserByUsernameAndPassword(user));
    }

    @Test
    public void testDeleteUser() throws Exception {
        System.out.println(userMapper.deleteUser(userMapper.getUserById(1)));
    }
}
