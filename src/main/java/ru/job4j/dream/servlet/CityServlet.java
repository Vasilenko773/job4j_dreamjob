package ru.job4j.dream.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.dream.model.City;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CityServlet extends HttpServlet {

    private final List<City> cities = new ArrayList<>(List.of(new City(1, "Москва"), new City(2, "Neryngri")));

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=utf-8");
        OutputStream output = resp.getOutputStream();
        for (City city : cities) {
            String json = GSON.toJson(city);
            output.write(json.getBytes(StandardCharsets.UTF_8));
            output.flush();
            output.close();
        }
    }
}
