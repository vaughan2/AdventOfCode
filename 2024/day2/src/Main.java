import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    static int safes = 0;


    public static void main(String[] args) {
        int trajectorySet = 0;
        int safe = 0; 
        int lines = 0;
        BufferedReader reader;
        try {
            reader = new BufferedReader(Files.newBufferedReader(Paths.get("list.txt"), StandardCharsets.UTF_8));
            String line = reader.readLine();

            // each Row
            while (line != null) {
                line = reader.readLine();
                System.out.println("Line: " + lines++);
                trajectorySet = 0;
                safe = 0;
                String[] numbers = line.split(" ");
                // inidividual numbers
                for (int i = 0; i < numbers.length; i++) {
                    if (safe == 2 || safe == 0) {
                        try {
                            if (trajectorySet == 0) {
                                trajectorySet = CheckTrajectory(Integer.parseInt(numbers[i]),
                                        Integer.parseInt(numbers[i + 1]));
                            }
                            if (trajectorySet >= 1) {
                                int trajectorySetTemp = CheckTrajectory(Integer.parseInt(numbers[i]),
                                        Integer.parseInt(numbers[i + 1]));
                                if (trajectorySet == trajectorySetTemp) {
                                    int abs = Math.abs(Integer.parseInt(numbers[i]) - Integer.parseInt(numbers[i + 1]));
                                    if (abs <= 3 && abs > 0) {
                                        safe = 2;
                                    } else {
                                        safe = 1;
                                    }
                                } else {
                                    trajectorySet = 0;
                                    safe = 1;
                                }
                            }

                        } catch (ArrayIndexOutOfBoundsException e) {
                            if (safe == 2) {
                                DetermineSafe();
                                trajectorySet = 0;
                                safe = 0;
                            }
                        }
                    } else {

                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(safes);
    }



    public static void DetermineSafe() {
        safes += 1;
    }

    public static int CheckTrajectory(int a, int b) {
        if (a < b) {
            return 1;
        }
        return 2;
    }

}

// the rules are simple
// either ther levels are all increasing or all decreasing
// also any two adjacent levels differ by atleast 1 to 3