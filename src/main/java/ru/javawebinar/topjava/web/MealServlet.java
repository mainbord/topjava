package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by mainbord on 28.03.17.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);
    private MealDao mealDao;

    public MealServlet() {
        super();
        mealDao = new MealDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LOG.debug("redirect to meals");
        System.out.println("____ Do Get");
        String action = "";
            if (request.getParameter("action") == null ){
                request.setAttribute("meals", mealDao.listMealsWithExceed());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
            }
            else {
                action = request.getParameter("action");
                if (action.equalsIgnoreCase("delete")){
                    int mealId = Integer.parseInt(request.getParameter("mealId"));
                    mealDao.remove(mealId);
                    request.setAttribute("meals", mealDao.listMealsWithExceed());
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
                } else if (action.equalsIgnoreCase("edit")){
                    int mealId = Integer.parseInt(request.getParameter("mealId"));
                    Meal meal = mealDao.getById(mealId);
                    System.out.println("");
                    request.setAttribute("meal", meal);
                    request.setAttribute("meals", mealDao.listMealsWithExceed());
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
                } else {
                    request.setAttribute("meals", mealDao.listMealsWithExceed());
                    request.getRequestDispatcher("/meals.jsp").forward(request, response);
                }
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("id") == null){
            LocalDateTime date = LocalDateTime.parse(req.getParameter("date"));

            byte ptext[] = req.getParameter("description").getBytes();
            String description = new String(ptext, "UTF-8");
            int calories = Integer.parseInt(req.getParameter("calories"));
            mealDao.add(new Meal(date,description,calories));
            req.setAttribute("meals", mealDao.listMealsWithExceed());
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }
        else {
            int id = Integer.parseInt(req.getParameter("id"));
            LocalDateTime date = LocalDateTime.parse(req.getParameter("date"));
            String description = (req.getParameter("description"));
            int calories = Integer.parseInt(req.getParameter("calories"));

            mealDao.update(id, date, description,calories);
            req.setAttribute("meals", mealDao.listMealsWithExceed());
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }


        System.out.println("___ DO Post");
    }
}
