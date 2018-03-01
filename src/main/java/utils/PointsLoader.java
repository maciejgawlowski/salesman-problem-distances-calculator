package utils;

import domain.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PointsLoader {

    public static List<Point> load(String fileName) {
        List<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.lines().forEach(line -> {
                String[] splited = line.split("\\s{2,}");
                Point point = new Point();
                point.setName(splited[0]);
                point.setLongitude(Double.valueOf(splited[1].substring(0, 2)) + Double.valueOf(splited[1].substring(2, 4)) / 60);
                point.setLatitude(Double.valueOf(splited[2].substring(0, 2)) + Double.valueOf(splited[2].substring(2, 4)) / 60);
                points.add(point);
            });
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }
}
