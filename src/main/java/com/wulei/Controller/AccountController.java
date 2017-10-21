package com.wulei.Controller;

import com.wulei.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody String login(HttpSession session,
                                      @RequestParam("username") String username,
                                      @RequestParam("password") String password){

        return "";
    }
}
