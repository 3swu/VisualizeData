import com.wulei.Beans.File;
import com.wulei.DAO.FileMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class FileMapperTest {
    @Autowired
    FileMapper fileMapper;

    @Test
    public void insertFileTest(){
        File file = new File(2,"file3","filePath",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(fileMapper.insertFile(file));
    }

    @Test
    public void getFileListByUserIdTest(){
        List<File> fileList = fileMapper.getFileListByUserId(1);
        for(File file : fileList)
            System.out.println(file);
    }

    @Test
    public void getFileByFileNameAndUserIdTest(){
        List<File> fileList = fileMapper.getFileListByUserId(1);
        System.out.println("fileList: " + fileList);
        for(File file : fileList){
            System.out.println(fileMapper.getFileByFileNameAndUserId(file));
        }
    }

    @Test
    public void getFileByFileId(){
        System.out.println(
                fileMapper.getFileByFileId(
                        fileMapper.getFileListByUserId(1).get(0).getFileId())
        );
    }

    @Test
    public void deleteFileTest(){
        System.out.println(
                fileMapper.deleteFile(
                        fileMapper.getFileListByUserId(1).get(0)
                )
        );
    }
}
