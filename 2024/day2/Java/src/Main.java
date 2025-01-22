import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    static int safes = 0;
    static int trajectorySet = 0;
    static int safe = 0;
    static int life = 1;

    public static void main(String[] args) {

        BufferedReader reader;

        try {
           
            reader = new BufferedReader(Files.newBufferedReader(Paths.get("list.txt"), StandardCharsets.UTF_8));
            String line = reader.readLine();

            // each Row
            while (line != null) {
                trajectorySet = 0;
                safe = 0;
                life = 1;
                String[] numbers = line.split(" ");
                ArrayList<Integer> numbersA = getIntegerArray(numbers);

                // inidividual numbers
                loopedNumbers(numbersA, life);

                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(safes);
    }

    public static void loopedNumbers(ArrayList<Integer> numbers, int life) {
        for (int i = 0; i < numbers.size(); i++) {
            if (i + 1 < numbers.size()) {
                if (safe == 2 || safe == 0) {
                    try {
                        if (trajectorySet == 0) {
                            trajectorySet = CheckTrajectory(numbers.get(i),
                                    numbers.get(i + 1));
                        }
                        if (trajectorySet >= 1) {
                            int trajectorySetTemp = CheckTrajectory(numbers.get(i), numbers.get(i + 1));
                            if (trajectorySet == trajectorySetTemp) {
                                int abs = Math.abs(numbers.get(i) - numbers.get(i + 1));
                                if (abs <= 3 && abs > 0) {
                                    safe = 2;
                                } else {
                                    if (life == 1) {
//                                       Issue with if the second number is the one that breaks this. not the first i.e. i not i+1
                                        numbers.remove(i);
                                        loopedNumbers(numbers, 0);
                                        return;
                                        // life = 0;
                                    } else {
                                        safe = 1;
                                    }
                                }
                            } else {
                                if (life == 1) {
//                                    Issue with if the second number is the one that breaks this. not the first i.e. i not i+1 
                                    numbers.remove(i);
                                    loopedNumbers(numbers, 0);
                                    return;
                                    // life = 0;
                                } else {
                                    trajectorySet = 0;
                                    safe = 1;
                                }
                            }
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }else{
                if (safe == 2) {
                    DetermineSafe();
                    trajectorySet = 0;
                    safe = 0;
                }
            }
        }

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

    private static ArrayList<Integer> getIntegerArray(String[] stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (String stringValue : stringArray) {
            try {
                // Convert String to Integer, and store it into integer array list.
                result.add(Integer.parseInt(stringValue));
            } catch (NumberFormatException nfe) {
            }
        }
        return result;
    }

}

// the rules are simple
// either their levels are all increasing or all decreasing
// also any two adjacent levels differ by atleast 1 to 3