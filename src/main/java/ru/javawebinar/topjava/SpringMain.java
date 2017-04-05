package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AbstractUserController;
import ru.javawebinar.topjava.web.user.AdminRestController;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            User adminUser = adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
            ProfileRestController profileRestController = appCtx.getBean(ProfileRestController.class);
            User user1 = profileRestController.create(new User(null, "userName1", "email1", "password1", Role.ROLE_USER));
            System.out.println("--------------------");
            for (User user:
                    adminUserController.getAll()) {
                System.out.println(user);
            }
            System.out.println("--------------------");
/*            System.out.println("____ " + adminUserController.get(1));
            adminUserController.delete(1);
            System.out.println("____ " + adminUserController.getAll());*/

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            System.out.println(mealRestController.get(1,1));
            Meal meal = new Meal(null, 2, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "222", 510);
            System.out.println("+++++++++++++++++++++");
            mealRestController.save(meal,1);
            System.out.println("+++++++++++++++++++++");
            mealRestController.save(meal,2);
            System.out.println("*******************");
            for (MealWithExceed meal2:
                 mealRestController.getAll(1,1000)) {
                System.out.println(meal2);
                
            }
            System.out.println("*******************");
//            mealRestController.save(MealsUtil.MEALS.get(0),1);
            System.out.println("___" + mealRestController.getAll(1,1));
        }
    }
}
