package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by mainbord on 04.04.17.
 */
public class UsersUtil {
    public static final List<User> users = new ArrayList<>();
    static {
        users.add(new User(null, "admin", "aaa@mail.ru", "0", 1000, true, EnumSet.of(Role.ROLE_USER)));
        users.add(new User(null, "user1", "bbb@yandex.ru", "0", 1000, true, EnumSet.of(Role.ROLE_USER)));
        users.add(new User(null, "user2", "ccc@gmail.com", "0", 1000, true, EnumSet.of(Role.ROLE_USER)));
    }

}
