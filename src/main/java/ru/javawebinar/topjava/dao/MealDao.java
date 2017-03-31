package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by mainbord on 29.03.17.
 */
public interface MealDao {
    public void add(Meal meal);

    public void update(Meal person);

    public void remove(int id);

    public Meal getById(int id);

    public List<MealWithExceed> listMealsWithExceed();
    public List<Meal> listMeals();
}
