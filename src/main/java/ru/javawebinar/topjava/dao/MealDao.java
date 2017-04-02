package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by mainbord on 29.03.17.
 */
public interface MealDao {
    public void add(Meal meal);

    public void update(int id, LocalDateTime date, String description, int calories);

    public void remove(int id);

    public Meal getById(int id);

    public List<MealWithExceed> listMealsWithExceed();
    public List<Meal> listMeals();
}
