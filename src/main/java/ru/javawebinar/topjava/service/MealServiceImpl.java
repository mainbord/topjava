package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Override
    public Meal save(Meal Meal) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Meal get(int id) {
        return null;
    }

    @Override
    public Collection<Meal> getAll() {
        return null;
    }
}