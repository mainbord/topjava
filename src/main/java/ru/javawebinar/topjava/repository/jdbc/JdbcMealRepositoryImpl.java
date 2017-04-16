package ru.javawebinar.topjava.repository.jdbc;

import org.apache.taglibs.standard.tag.common.sql.DataSourceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcMealRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//    public JdbcMealRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("dateTime", meal.getDateTime())
                .addValue("description", meal.getDescription())
                .addValue("calories", meal.getCalories())
                .addValue("user_id", userId);

        if (meal.isNew()) {
            Number newKey = insertMeal.executeAndReturnKey(map);
            meal.setId(newKey.intValue());
        } else {
            namedParameterJdbcTemplate.update(
                    "UPDATE meals SET datetime='" + meal.getDateTime() + "', description='" + meal.getDescription() + "', calories=" + meal.getCalories() + ", user_id=" + userId + " WHERE id= " + meal.getId() + " AND user_id = " + userId, map);
        }

/*        if (meal == null || meal.getId() == null){
            jdbcTemplate.update("INSERT INTO meals(datetime, description, calories, user_id) VALUES " +
                    "('" + meal.getDateTime() + "' , '" + meal.getDescription() + "', " + meal.getCalories() + ", " + userId + ")");
        } else {
            String sqlStatement = "UPDATE meals SET datetime='" + meal.getDateTime() + "', description='" + meal.getDescription() + "', calories=" + meal.getCalories() + ", user_id=" + userId + " WHERE id= " + meal.getId() + " AND user_id = " + userId;
            jdbcTemplate.update(sqlStatement);
        }*/
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE  FROM meals WHERE id= " + id + " AND user_id = " + userId) > 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> list = jdbcTemplate.query("SELECT * FROM meals WHERE user_id=" +userId + " AND id=" + id, ROW_MAPPER);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=" + userId + " ORDER BY datetime DESC", ROW_MAPPER);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        String query = "SELECT * FROM meals WHERE datetime BETWEEN " + "'" + startDate +"'" + " AND '" + endDate + "' AND user_id=" + userId + " ORDER BY datetime";
        return jdbcTemplate.query(query, ROW_MAPPER);
    }
}
