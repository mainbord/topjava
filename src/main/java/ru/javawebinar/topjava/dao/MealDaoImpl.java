package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

/**
 * Created by mainbord on 29.03.17.
 */
public class MealDaoImpl implements MealDao{

    @Override
    public void add(Meal meal) {

    }

    @Override
    public void update(Meal person) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Meal getById(int id) {
        return null;
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
