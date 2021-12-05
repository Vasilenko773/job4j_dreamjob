package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.io.IOException;
import java.util.Scanner;

public class MainStore {

    public static void main(String[] args) throws IOException {
        Store store = DbStore.instOf();
        store.save(new Post(0, "Java Job"));
        store.save(new Post(1, "Job Job"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }

        System.out.println(store.findById(1));

        store.saveCnd(new Candidate(0, "Java bos"));
        store.saveCnd(new Candidate(1, "Java cocos"));
        for (Candidate c : store.findAllCandidates()) {
            System.out.println(c.getId() + " " + c.getName());
        }
        store.saveUser(new User("Alex", "mail@", "qwerty"));
        System.out.println(store.findByEmailUser("mail@").getPassword());
    }
}


