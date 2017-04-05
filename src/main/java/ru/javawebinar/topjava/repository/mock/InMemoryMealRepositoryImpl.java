package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getUserId() == null) {return null;}
        boolean old = meal.isNew();
        if (old) {meal.setId(counter.incrementAndGet());}
        Meal mealRep = repository.get(meal.getId());
        if (mealRep == null && old) {
            repository.put(meal.getId(), meal);
            return meal;
        } else if (mealRep != null && ! old) {
            if (mealRep.getUserId().equals(meal.getUserId())) {
                repository.put(meal.getId(), meal);
                return meal;
            }
        }
        return null;
    }


    /*3.1: если еда отсутствует или чужая, возвращать null/false (см. комментарии в UserRepository)*/
    public Meal save(Meal meal, Integer userID) {
        if (meal == null || userID == null) return null;
        boolean old = meal.isNew();
        if (old) {meal.setId(counter.incrementAndGet());}
        Meal mealRep = repository.get(meal.getId());
        if (mealRep == null && old) {
            repository.put(meal.getId(), meal);
            return meal;
        }
        return null;
    }

    @Override
    public boolean delete(int id, Integer userId)
    {
        Predicate<Meal> p1 = meal -> meal.getId() == id && meal.getUserId().equals(userId);
        return repository.values().stream().anyMatch(p1) && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, Integer userId) {
        Meal tempMeal = repository.get(id);
        return tempMeal == null ? null : tempMeal.getUserId().equals(userId) ? tempMeal : null;
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {

        return repository.values().stream()
                .filter(meal -> Objects.equals(meal.getUserId(), userId))
                .sorted(Comparator.comparing(Meal::getTime).reversed())
                .collect(Collectors.toList());
    }


}

