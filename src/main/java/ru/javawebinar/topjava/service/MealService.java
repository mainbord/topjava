package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealService {

    Meal save(Meal Meal);

    boolean delete(int id);

    Meal get(int id);

    Collection<Meal> getAll();
}