package com.wulei.DAO;

import com.wulei.Beans.User;
import org.springframework.stereotype.Repository;

/**
 * Created by wulei on 2017/10/21
 * DAO interface:UserMapper->UserMapper.xml
 */
public interface UserMapper {
    public User getUserById(int id);

    public User getUserByUsername(String username);

    //参数user中必须包含username成员和password成员
    public User getUserByUsernameAndPassword(User user);

    public int insertUser(User user);

    public int deleteUser(User user);
}
