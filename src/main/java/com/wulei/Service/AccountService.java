package com.wulei.Service;


import com.wulei.Beans.User;

import javax.servlet.http.HttpSession;

public interface AccountService {
    boolean login(HttpSession session, User user);

    boolean logout(HttpSession session);

    boolean register(User user);
}
