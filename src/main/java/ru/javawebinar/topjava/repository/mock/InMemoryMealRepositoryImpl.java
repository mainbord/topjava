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
        MealsUtil.MEALS.forEach(meal -> this.save(meal,1));
    }

    @Override
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
        return userId != null && repository.entrySet()
                .removeIf(entry -> entry.getKey().equals(id) &&
                        Objects.equals(entry.getValue().getUserId(), userId));
    }

    @Override
    public Meal get(int id, Integer userId) {
        Meal meal = repository.get(id);
        return meal == null ? null : meal.getUserId().equals(userId) ? meal : null;
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {

        return repository.values().stream()
                .filter(meal -> Objects.equals(meal.getUserId(), userId))
                .sorted(Comparator.comparing(Meal::getTime).reversed())
                .collect(Collectors.toList());
    }


}

