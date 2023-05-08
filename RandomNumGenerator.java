import java.io.*;
import java.util.*;

public class RandomNumGenerator {
    public static void main(String[] args) {
        // Create a new file
        File file = new File("input.txt");
        Random random = new Random();
        // Create a set with unique numbers
        Set<Integer> set = new LinkedHashSet<>();
        while (set.size() < 10000) {
            int num = random.nextInt(10001);
            set.add(num);
        }
        try {
            // Create a FileWriter object to write to the file
            FileWriter writer = new FileWriter(file);
            for (int num : set) {
                // Write the count of numbers to string
                writer.write(num + "\n");
            }

        // Close the writer object
        writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}