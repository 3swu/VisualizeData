package com.wulei.Controller;

import com.wulei.Beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @RequestMapping(value = "/showlogin", method = RequestMethod.GET)
    public String showLogin(){
        return "login";
    }

    @RequestMapping(value = "/main/{username}", method = RequestMethod.GET)
    public String main(@PathVariable(value = "username") String username, HttpSession session){
        User user = (User)session.getAttribute("user");
        if(username.equals(user.getUsername().substring(0,user.getUsername().indexOf("@")))){
            return "main";
        }

        return "login";
    }
}
