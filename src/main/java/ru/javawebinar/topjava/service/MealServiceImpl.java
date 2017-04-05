package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    @Override
    public void save(Meal meal, Integer userId) {
        meal.setUserId(userId);
        ValidationUtil.checkNotFoundWithId(mealRepository.save(meal), meal.getId());
    }

    @Override
    public void delete(int id, Integer userId) {
        ValidationUtil.checkNotFoundWithId(mealRepository.delete(id, userId), id);
    }

    @Override
    public Meal get(Integer id, Integer userId) {
        return ValidationUtil.checkNotFoundWithId(mealRepository.get(id, userId), id);
    }

    @Override
    public Collection<MealWithExceed> getAll(Integer userId, int calories) {
        return MealsUtil.getWithExceeded(
                mealRepository.getAll(userId), calories);
    }
}