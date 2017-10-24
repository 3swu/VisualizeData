import com.wulei.Service.XlsxResolveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-service.xml")
public class XlsxResolveServiceTest {

    @Autowired
    private XlsxResolveService xlsxResolveService;

    @Test
    public void getSheetContentTest(){
        xlsxResolveService.initFile("C:\\Users\\wulei\\Desktop\\UploadFile\\files\\resources0.xlsx");
        System.out.println(xlsxResolveService.getContentBySheetName("总柱状图"));
    }
}
