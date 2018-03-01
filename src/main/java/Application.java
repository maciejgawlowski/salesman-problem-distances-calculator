import domain.Point;
import utils.DistanceCalculator;
import utils.PointsLoader;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    private static final String FILENAME_MAIN_CITIES = "C:/Users/macie/IdeaProjects/salesman-problem-distances-calculator/src/main/resources/main_polish_cities.txt";
    private static final String FILENAME_ALL_CITIES = "C:/Users/macie/IdeaProjects/salesman-problem-distances-calculator/src/main/resources/polish_cities.txt";
    private static final String OUTPUT_DISTANCES_FILENAME_MAIN_CITIES = "C:/Users/macie/IdeaProjects/salesman-problem-distances-calculator/src/main/resources/main_polish_cities_distances.txt";
    private static final String OUTPUT_DISTANCES_FILENAME_ALL_CITIES = "C:/Users/macie/IdeaProjects/salesman-problem-distances-calculator/src/main/resources/polish_cities_distances.txt";

    public static void main(String[] args) {
        processPoints(FILENAME_MAIN_CITIES, OUTPUT_DISTANCES_FILENAME_MAIN_CITIES);
        processPoints(FILENAME_ALL_CITIES, OUTPUT_DISTANCES_FILENAME_ALL_CITIES);
    }

    private static void processPoints(String inputPath, String outputPath) {
        List<Point> points = PointsLoader.load(inputPath);

        Map<List<String>, Long> distances = new HashMap<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Long distance = DistanceCalculator.calculate(points.get(i), points.get(j));
                distances.put(Arrays.asList(points.get(i).getName(), points.get(j).getName()), distance);
            }
        }

        saveDistancesToFile(distances, outputPath);
    }

    private static void saveDistancesToFile(Map<List<String>, Long> distances, String fileName) {
        try (PrintWriter out = new PrintWriter(fileName)) {
            distances.forEach((pointsIds, distance) -> out.println(pointsIds.get(0) + "  " + pointsIds.get(1) + "  " + distance));
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
