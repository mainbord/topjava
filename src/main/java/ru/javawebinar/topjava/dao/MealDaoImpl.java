package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by mainbord on 29.03.17.
 */
public class MealDaoImpl implements MealDao{

    @Override
    public void add(Meal meal) {
        synchronized (MealsUtil.meals) {
            MealsUtil.meals.add(meal);
        }
    }

    @Override
    public void update(int id, LocalDateTime date, String description, int calories) {
            MealsUtil.meals.remove(id);
            MealsUtil.meals.add(new Meal(id, date, description, calories));
    }

    @Override
    public void remove(int id) {
            MealsUtil.meals.remove(id);
    }

    @Override
    public Meal getById(int id) {
        return MealsUtil.meals.get(id);
    }

    @Override
    public List<MealWithExceed> listMealsWithExceed() {
        return MealsUtil.getWithExceeded(MealsUtil.meals, 2000);
    }

    @Override
    public List<Meal> listMeals() {
        return MealsUtil.meals;
    }
}
