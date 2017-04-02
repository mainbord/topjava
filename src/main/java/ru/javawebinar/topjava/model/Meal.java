package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GKislin
 * 11.01.2015.
 */
public class Meal {

    private int id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(int id, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {

        if (MealsUtil.meals.size() == 0){this.id = 0;}
        else if (MealsUtil.meals.size() - 1 ==
                MealsUtil.meals.get((MealsUtil.meals.size() -1)).getId()) {
            this.id = MealsUtil.meals.size();
        }
        else {
            List<Integer> ids = new ArrayList<>();
            for (int i = 0; i < MealsUtil.meals.size(); i++)
            {
                ids.add(MealsUtil.meals.get(i).getId());
            }
            for (int i = 0; i < ids.size(); i++) {
                if (!ids.contains(i)) {
                    this.id = i;
                    break;
                }
            }
        }
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public int getId() {return id;}

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
