package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Repository
public interface MealRepository {
    Meal save(Meal meal, Integer userID);

    boolean delete(int id, Integer userId);

    Meal get(int id, Integer userId);

    Collection<Meal> getAll(Integer userId);
}
