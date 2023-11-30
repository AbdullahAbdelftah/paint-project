package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController

public class JsonToObject {
    @postMapping("/circles")
    public List circles(@RequestBody String circles) {
        circles = circles.replaceAll("\"", "");
        String jsonStringFromFrontend = circles;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Circle>>() {}.getType();
        List<Circle> circle = gson.fromJson(jsonStringFromFrontend, listType);
        for (Circle c : circle) {
            System.out.println(c);
        }
        return circle;
    }
}
