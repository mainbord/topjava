package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.Collection;

public interface MealService {

    void save(Meal meal, Integer userId);

    void delete(int id, Integer userId);

    Meal get(Integer id, Integer userId);

    Collection<MealWithExceed> getAll(Integer userId, int calories);
}