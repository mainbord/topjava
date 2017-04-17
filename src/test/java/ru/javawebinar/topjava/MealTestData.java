package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getCalories(), actual.getCalories())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                    )
    );

    public static final int MEAL_ID = 100002;
    public static final Meal MEAL = new Meal(MEAL_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;



}
