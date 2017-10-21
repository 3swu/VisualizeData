import com.wulei.Beans.User;
import com.wulei.Service.AccountService;
import com.wulei.Service.Impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-service.xml")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void registerTest(){
        User user = new User();
        user.setUsername("1046828876@qq.com");
        user.setPassword("123456");
        System.out.println(accountService.register(user));
    }
}
