package com.wulei.Service.Impl;

import com.wulei.Beans.User;
import com.wulei.DAO.UserMapper;
import com.wulei.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wulei on 2017/10/21
 * 实现账户管理服务接口
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserMapper userMapper;
    /**
     *
     * @param session
     * @param user 封装了用户登录时传入的username和password
     * @return 登录成功，返回true，否则返回false
     */
    public boolean login(HttpSession session, User user) {
        User resultUser = null;

        if(user.getUsername() !=null && user.getPassword() != null){
            resultUser = userMapper.getUserByUsernameAndPassword(user);
            if(resultUser != null) {
                session.setAttribute("user", resultUser);
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param session
     * @return 如果当前session中有user属性，则注销，返回true。
     *          如果session中没有user属性，则返回false
     */
    public boolean logout(HttpSession session) {
        if(session.getAttribute("user") !=null){
            session.removeAttribute("user");
            return true;
        }
        return false;
    }

    /**
     *
     * @param user 封装了用户注册时传入的username和password
     * @return 如果注册成功，返回true，否则，返回false
     */
    public boolean register(User user) {
        if(user.getUsername() != null && user.getPassword() != null){
            User resultUser = userMapper.getUserByUsername(user.getUsername());
            if(resultUser == null){
                user.setRegtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                if(userMapper.insertUser(user) == 1)
                    return true;
            }
        }
        return false;
    }

}
