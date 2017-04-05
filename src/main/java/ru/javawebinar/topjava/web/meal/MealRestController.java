package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.Collection;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    public void save(Meal meal, Integer userId) {
        service.save(meal, userId);
    }

    public void delete(int id, Integer userId) {
        service.delete(id, userId);
    }

    public Meal get(Integer id, Integer userId) {
        return service.get(id, userId);
    }

    public Collection<MealWithExceed> getAll(Integer userId, int calories) {
        return service.getAll(userId,calories);
    }

}