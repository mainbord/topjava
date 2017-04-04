package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getUserId() == null)
            return null;
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
/*        repository.put(meal.getId(), meal);
        return meal;*/
        Meal mealRep = repository.get(meal.getId());
        if (mealRep == null && meal.isNew()) {
            repository.put(meal.getId(), meal);
            return meal;
        } else if (mealRep != null && !meal.isNew()) {
            if (mealRep.getUserId().equals(meal.getUserId())) {
                repository.put(meal.getId(), meal);
                return meal;
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id, Integer userId)
    {
        Predicate<Meal> p1 = meal -> meal.getId() == id && meal.getUserId().equals(userId);

        return repository.remove(id) != null && repository.values().stream().anyMatch(p1);
    }

    @Override
    public Meal get(int id, Integer userId) {
        Meal temlMeal = repository.get(id);
        return repository.get(id) == null ? null : repository.get(id).getUserId().equals(userId) ? repository.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {

        return repository.values().stream()
                .filter(meal -> Objects.equals(meal.getUserId(), userId))
                .sorted(Comparator.comparing(Meal::getTime))
                        .collect(Collectors.toList());
    }


}

