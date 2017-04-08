package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);

//    private MealRepository repository;

    private ConfigurableApplicationContext appCtx;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //repository = new InMemoryMealRepositoryImpl();
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                null,
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);

        MealRestController mealRestController = appCtx.getBean(MealRestController.class);
        mealRestController.save(meal, AuthorizedUser.id());
        //repository.save(meal, AuthorizedUser.id());
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        MealRestController mealRestController = appCtx.getBean(MealRestController.class);
        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                LOG.info("Delete {}", id);
                mealRestController.delete(id, AuthorizedUser.id());
                //repository.delete(id, AuthorizedUser.id());
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
//                id = getId(request);
                final Meal meal = action.equals("create") ?
                        new Meal(null, null, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealRestController.get(getId(request), AuthorizedUser.id());
                        //repository.get(getId(request), AuthorizedUser.id());
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "all":
            default:
                LOG.info("getAll");
                request.setAttribute("meals",
//                        MealsUtil.getWithExceeded(
                                mealRestController.getAll(AuthorizedUser.id(),AuthorizedUser.getCaloriesPerDay()));
//                                repository.getAll(AuthorizedUser.id()), MealsUtil.DEFAULT_CALORIES_PER_DAY)
//                );
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    @Override
    public void destroy() {
        try {
            if (appCtx != null) appCtx.close();
        }
        catch (Exception e){
            //Here we can not do anything
        }
        super.destroy();
    }
}