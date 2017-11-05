package com.wulei.Controller;

import com.wulei.Beans.User;
import com.wulei.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 *Created by wulei on 2017/10/21
 * AccountController
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     *登录
     * @param session
     * @param username
     * @param password
     * @return 返回json数据，登录成功返回"success",登录失败返回"failed"
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody String login(HttpSession session,
                                      @RequestParam("username") String username,
                                      @RequestParam("password") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(accountService.login(session,user)){
            return "success";
        }
        return "failed";
    }

    /**
     * 注销用户
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody String logout(HttpSession session){
        if(accountService.logout(session))
            return "success";
        return "failed";
    }

    /**
     * 当前登录的用户
     * @param session
     * @return
     */
    @RequestMapping(value = "/nowuser", method = RequestMethod.GET)
    public @ResponseBody User nowUser(HttpSession session){
        return (User) session.getAttribute("user");
    }
}
