create TABLE candidate
(
    id      SERIAL PRIMARY KEY,
    name    TEXT,
    cityId  int references cities (id),
    created timestamp
);