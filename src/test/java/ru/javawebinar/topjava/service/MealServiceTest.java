package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.MealTestData;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by mainbord on 17.04.17.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();

    }

    @Test
    public void get() throws Exception {
        System.out.println("de");
        Meal meal = service.get(MEAL_ID, USER_ID);
        MATCHER.assertEquals(MEAL, meal);
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void getBetweenDateTimes() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

}