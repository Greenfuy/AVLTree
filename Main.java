import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        File input = new File("input.txt");
        File insertOutput = new File("insert_output.txt");
        File findOutput = new File("find_output.txt");
        File deleteOutput = new File("delete_output.txt");
        Random random = new Random();
        AVLTree tree = new AVLTree();
        try {
            Scanner scanner = new Scanner(input);
            List<Integer> allNumbers = new ArrayList<>();
            while (scanner.hasNextLine()) {
                allNumbers.add(Integer.parseInt(scanner.nextLine()));
            }
            long[][] add = tree.addElem(allNumbers);

            Set<Integer> set1 = new LinkedHashSet<>();
            while (set1.size() < 100) {
                set1.add(allNumbers.get(random.nextInt(10000)));
            }
            long[][] find = tree.findElem(set1.stream().toList());

            Set<Integer> set2 = new LinkedHashSet<>();
            while (set2.size() < 1000) {
                set2.add(allNumbers.get(random.nextInt(10000)));
            }
            long[][] remove = tree.removeElem(set2.stream().toList());

            writeToFile(insertOutput, add);
            writeToFile(findOutput, find);
            writeToFile(deleteOutput, remove);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(File file, long[][] data) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (long[] datum : data) {
            writer.write(datum[0] + " " + datum[1] + "\n");
        }
        writer.close();
    }
}
