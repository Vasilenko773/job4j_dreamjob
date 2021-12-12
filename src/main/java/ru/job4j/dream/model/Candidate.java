package ru.job4j.dream.model;

import ru.job4j.dream.store.DbStore;

import java.time.LocalDateTime;
import java.util.Objects;

public class Candidate {
    private int id;
    private String name;
    private int cityId;
    private LocalDateTime created = LocalDateTime.now();

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
        this.created = LocalDateTime.now();
    }

    public Candidate(int id, String name, LocalDateTime created, int cityId) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id
                && Objects.equals(name, candidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}