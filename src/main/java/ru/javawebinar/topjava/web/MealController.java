package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

/**
 * Created by mainbord on 07.05.17.
 */
@Controller
public class MealController {
    private static final Logger LOG = LoggerFactory.getLogger(MealController.class);

    @Autowired
    private final MealService service;

    @Autowired
    public MealController(MealService service) {
        this.service = service;
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String meals(Model model) {
        int userId = AuthorizedUser.id();
        LOG.info("getAll for User {}", userId);
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @RequestMapping(value = "/meal/{id}", method = RequestMethod.GET)
    public String meal(Model model, @PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        LOG.info("get meal {} for User {}", id, userId);
        model.addAttribute("meal", service.get(id, userId));
        return "meal";
    }

    @RequestMapping(value = "/meal/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        LOG.info("delete meal {} for User {}", id, userId);
        service.delete(id, userId);
        return "redirect:/meals";
    }

    @RequestMapping(value = "/meal/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("meal") Meal meal, HttpServletRequest request) {
        if (request.getParameter("id").isEmpty()) {
            create1(meal);
        } else {
            update1(meal, getId(request));
        }
        return "redirect:/meals";
    }


    public Meal create1(Meal meal) {
        int userId = AuthorizedUser.id();
        checkNew(meal);
        LOG.info("create {} for User {}", meal, userId);
        return service.save(meal, userId);
    }

    public void update1(Meal meal, int id) {
        int userId = AuthorizedUser.id();
        checkIdConsistent(meal, id);
        LOG.info("update {} for User {}", meal, userId);
        service.update(meal, userId);
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }


    @RequestMapping(value = "/meal/create", method = RequestMethod.GET)
    public String create(Model model, HttpServletRequest request) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        request.setAttribute("action", "create");
        return "meal";
    }

    @RequestMapping(value = "/meal/filter", method = RequestMethod.POST)
    public String filter(Model model, HttpServletRequest request) {

        LocalDate startDate = DateTimeUtil.parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = DateTimeUtil.parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = DateTimeUtil.parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = DateTimeUtil.parseLocalTime(request.getParameter("endTime"));

        int userId = AuthorizedUser.id();
        LOG.info("getAll for User {}", userId);
        System.out.println("________________");
        List<MealWithExceed> ll= MealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                        endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay());
        int ii = ll.size();
        model.addAttribute("meals", MealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                        endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }
}
