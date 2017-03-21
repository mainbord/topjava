package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );

        long dateStart = new Date().getTime();
        List<UserMealWithExceed> list = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        long dateFinish = new Date().getTime();

        System.out.println("Потратил времени: " + (dateFinish - dateStart));
        System.out.println();
        System.out.println(".....Analyzing time complexity.....");

        for (int k = 20000; k <= 10240000; k*=2) {
            int count = 0;
            int time = 0;
            for (int h = 0; h < 30; h++) {
                List<UserMeal> mlist = new ArrayList<>();
                int cal = 500;
                for (int i = 0; i < k; i++) {
                    mlist.add(new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", cal++));
                }

                long dateStart1 = new Date().getTime();
                List<UserMealWithExceed> list2 = getFilteredWithExceeded(mlist, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
                long dateFinish1 = new Date().getTime();
                time += dateFinish1 - dateStart1;
//                System.out.println("k = " + k + " Потратил времени: " + (dateFinish1 - dateStart1));
                count++;
            }
            System.out.println(k + " " + (time/count));
        }

        for (UserMealWithExceed meal:
             list) {
            System.out.println(meal);
        }
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        Map<LocalDate,Integer> map = new TreeMap<>();
        mealList
                .forEach((usermeal) -> {
                    if (map.containsKey(usermeal.getDateTime().toLocalDate())){
                        map.put(usermeal.getDateTime().toLocalDate(), map.get(usermeal.getDateTime().toLocalDate()) + usermeal.getCalories());
                    }
                    else {map.put(usermeal.getDateTime().toLocalDate(),usermeal.getCalories());}

                });

        List<UserMealWithExceed> listExceed = new LinkedList<>();

        mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                .forEach((usermeal) -> listExceed.add(new UserMealWithExceed(usermeal.getDateTime(), usermeal.getDescription(), usermeal.getCalories(), map.get(usermeal.getDateTime().toLocalDate()) > caloriesPerDay)));

        return listExceed;
    }
}
