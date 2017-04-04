package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.Collection;

public class MealServiceImpl implements MealService {

    private MealRepository repository;

    @Override
    public void save(Meal meal, Integer userId) {
        meal.setUserId(userId);
        ValidationUtil.checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    @Override
    public void delete(int id, Integer userId) {
        ValidationUtil.checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Meal get(Integer id, Integer userId) {
        return ValidationUtil.checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public Collection<MealWithExceed> getAll(Integer userId, int calories) {
        return MealsUtil.getWithExceeded(
                repository.getAll(userId), calories);
    }
}