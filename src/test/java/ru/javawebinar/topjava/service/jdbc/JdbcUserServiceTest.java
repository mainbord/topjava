package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

/**
 * Created by mainbord on 25.04.17.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JDBC})
public class JdbcUserServiceTest extends UserServiceTest {
}