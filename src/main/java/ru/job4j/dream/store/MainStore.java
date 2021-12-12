package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.io.IOException;
import java.util.Scanner;

public class MainStore {

    public static void main(String[] args) throws IOException {
        Store store = DbStore.instOf();
        System.out.println(store.findByIdCity(1).getName());
    }
}



