package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController

public class JsonToObject {
    public static List<Circle> circle=new ArrayList<>();
    public static List<Square> square=new ArrayList<>();
    public static List<Rectangle> rectangle=new ArrayList<>();
    public static List<Ellipse> ellipse=new ArrayList<>();
    public static List<Triangle> triangle=new ArrayList<>();
    public static List<Line> line=new ArrayList<>();
    @PostMapping("/circles")
    public void circles(@RequestBody String circles) {
        circles = circles.replaceAll("\"", "");
        String jsonStringFromFrontend = circles;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Circle>>() {}.getType();
        circle = gson.fromJson(jsonStringFromFrontend, listType);
        for (Shape c : circle) {
            System.out.println(c);
        }
    }
    @PostMapping("/squares")
    public void squares(@RequestBody String squares) {
        squares = squares.replaceAll("\"", "");
        String jsonStringFromFrontend = squares;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Square>>() {}.getType();
        square = gson.fromJson(jsonStringFromFrontend, listType);
        for (Shape c : square) {
            System.out.println(c);
        }
    }
    @PostMapping("/rectangles")
    public void rectangles(@RequestBody String rectangles) {
        rectangles = rectangles.replaceAll("\"", "");
        String jsonStringFromFrontend = rectangles;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Rectangle>>() {}.getType();
        rectangle = gson.fromJson(jsonStringFromFrontend, listType);
        for (Shape c : rectangle) {
            System.out.println(c);
        }
    }
    @PostMapping("/ellipses")
    public void ellipses(@RequestBody String ellipses) {
        ellipses = ellipses.replaceAll("\"", "");
        String jsonStringFromFrontend = ellipses;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Ellipse>>() {}.getType();
        ellipse = gson.fromJson(jsonStringFromFrontend, listType);
        for (Shape c : ellipse) {
            System.out.println(c);
        }
    }
    @PostMapping("/triangles")
    public void triangles(@RequestBody String triangles) {
        triangles = triangles.replaceAll("\"", "");
        String jsonStringFromFrontend = triangles;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Triangle>>() {}.getType();
        triangle = gson.fromJson(jsonStringFromFrontend, listType);
        for (Shape c : triangle) {
            System.out.println(c);
        }
    }
    @PostMapping("/lines")
    public void lines(@RequestBody String lines) {
        lines = lines.replaceAll("\"", "");
        String jsonStringFromFrontend = lines;
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Line>>() {}.getType();
        line = gson.fromJson(jsonStringFromFrontend, listType);
        for (Shape c : line) {
            System.out.println(c);
        }
    }

    @PostMapping("/writeJson")
    public void writeJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonCircle = gson.toJson(circle);
        String jsonSquare = gson.toJson(square);
        String jsonRectangle = gson.toJson(rectangle);
        String jsonEllipse = gson.toJson(ellipse);
        String jsonTriangle = gson.toJson(triangle);
        String jsonLine = gson.toJson(line);
        try {
            FileWriter writer = new FileWriter("src/main/java/com/example/demo/shapes.json");
            writer.write("[");
            writer.write(jsonCircle);
            writer.write(",");
            writer.write(jsonSquare);
            writer.write(",");
            writer.write(jsonRectangle);
            writer.write(",");
            writer.write(jsonEllipse);
            writer.write(",");
            writer.write(jsonTriangle);
            writer.write(",");
            writer.write(jsonLine);
            writer.write("]");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/readJson")
    public String readJson() {
        try {
            FileReader fileReader = new FileReader("src/main/java/com/example/demo/shapes.json");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            String jsonString = jsonStringBuilder.toString();
            System.out.println("JSON Content: " + jsonString);
            bufferedReader.close();
            fileReader.close();
            return jsonString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}