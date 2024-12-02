import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int total = 0;
    static int similarityScore = 0;
    static ArrayList<Integer> leftBrain = new ArrayList<Integer>();
    static ArrayList<Integer> rightBrain = new ArrayList<Integer>();

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(Files.newBufferedReader(Paths.get("list.txt"), StandardCharsets.UTF_8));
            String line = reader.readLine();

            while (line != null) {
                String[] splited = line.split("\\s+");
                leftBrain.add(Integer.parseInt(splited[0]));
                rightBrain.add(Integer.parseInt(splited[1]));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(leftBrain);
        Collections.sort(rightBrain);
        measureBetween(leftBrain, rightBrain);
        for (int i : leftBrain) {
            similarityScore(i);

        }
        System.out.println("Similarity Score: " + similarityScore);
        System.out.println("Day1: " + total);

    }

    public static void measureBetween(ArrayList<Integer> leftArray, ArrayList<Integer> rightArray) {
        for (int i = 0; i < leftArray.size(); i++) {
            total += Math.abs(leftArray.get(i) - rightArray.get(i));
        }

    }

    public static void similarityScore(int check) {
        int score = Collections.frequency(rightBrain, check);

        int tempScore = check * score;
        similarityScore += tempScore;
    }

}