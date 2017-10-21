package com.wulei.Service;


import com.wulei.Beans.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public interface AccountService {
    boolean login(HttpSession session, User user);

    boolean logout(HttpSession session);

    boolean register(User user);
}
