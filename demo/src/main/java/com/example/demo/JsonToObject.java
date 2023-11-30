package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController

public class JsonToObject {
    @PostMapping("/circles")
    public void circles(@RequestBody String circles) {
        circles = circles.replaceAll("\"", "");
        String jsonStringFromFrontend = circles;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Circle>>() {}.getType();
        List<Circle> circle = gson.fromJson(jsonStringFromFrontend, listType);
        for (Circle c : circle) {
            System.out.println(c);
        }
    }
    @PostMapping("/squares")
    public void squares(@RequestBody String squares) {
        squares = squares.replaceAll("\"", "");
        String jsonStringFromFrontend = squares;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Square>>() {}.getType();
        List<Square> square = gson.fromJson(jsonStringFromFrontend, listType);
        for (Square c : square) {
            System.out.println(c);
        }
    }
    @PostMapping("/rectangles")
    public void rectangles(@RequestBody String rectangles) {
        rectangles = rectangles.replaceAll("\"", "");
        String jsonStringFromFrontend = rectangles;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Rectangle>>() {}.getType();
        List<Rectangle> rectangle = gson.fromJson(jsonStringFromFrontend, listType);
        for (Rectangle c : rectangle) {
            System.out.println(c);
        }
    }

    @PostMapping("/writeJson")
    public void writeJson(List<Circle> circle , List<Square> square , List<Rectangle> rectangle) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonCircle = gson.toJson(circle);
        String jsonSquare = gson.toJson(square);
        String jsonRectangle = gson.toJson(rectangle);
        try {
            FileWriter writer = new FileWriter("src/main/java/com/example/demo/shapes.json");
            writer.write(jsonCircle);
            writer.write(jsonSquare);
            writer.write(jsonRectangle);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
