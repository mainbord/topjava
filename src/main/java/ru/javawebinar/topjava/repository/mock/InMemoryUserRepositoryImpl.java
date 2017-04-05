package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.NamedEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer,User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    {
        for (User user:
                UsersUtil.users) {
            save(user);
        }
        System.out.println(repository.size());
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);

        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(),user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        List<User> users = new ArrayList<>(repository.values());
        Collections.sort(users, Comparator.comparing(NamedEntity::getName));
        return users;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);

        List<User> templist = repository.values().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .collect(Collectors.toList());
        return templist.size() >= 1 ? templist.get(0) : null;

/*        for (Map.Entry<Integer,User> pair:
             repository.entrySet()) {
            if (pair.getValue().getEmail().equals(email)){
                return pair.getValue();
            }
        }
        return null;*/
    }
}
