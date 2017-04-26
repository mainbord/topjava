package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by mainbord on 25.04.17.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JPA})
public class JpaMealServiceTest extends MealServiceTest {
}