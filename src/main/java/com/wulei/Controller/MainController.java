package com.wulei.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(value = "/showlogin", method = RequestMethod.GET)
    public String showLogin(){
        return "login";
    }
}
