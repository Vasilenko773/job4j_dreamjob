package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store {

    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post);

    void saveCnd(Candidate cnd);

    Post findById(int id);

    Candidate findByIdCnd(int id);

    Candidate deleteCnd(int id);

    void saveUser(User user);

    User findByEmailUser(String email);

    City findByIdCity(int id);

    Collection<City> findAllCity();

    City findCityByName(String name);
}