import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class ReaderAndGenerator {
    public static void main(String[] args) {
        int n, k, l;
        int[] numbers;

        // Reads Integers from Console
        Scanner input = new Scanner(System.in);
        System.out.print("What is N? (amount of numbers) ");
        n = input.nextInt();

        System.out.print("What is K? (min range) ");
        k = input.nextInt();

        System.out.print("What is L? (max range) ");
        l = input.nextInt();

        // Generates random numbers
        Random random = new Random();
        numbers = new int[n];

        random.setSeed(1);

        for (int i = 0; i < n; i++) {
            int number;
            do {
                number = random.nextInt();
            } while (!(number <= l && number >= k));

            numbers[i] = number;
        }

        // Writes to 3 separate files
        try {
            FileWriter[] writers = new FileWriter[3];
            StringBuilder[] builders = new StringBuilder[3];
            int[] upSort = new int[n];
            Integer[] downSort = new Integer[n];

            for (int i = 0; i < n; i++) {
                upSort[i] = numbers[i];
                downSort[i] = numbers[i];
            }

            Arrays.sort(upSort);
            Arrays.sort(downSort, Collections.reverseOrder());

            writers[0] = new FileWriter("src/out1.txt");
            writers[1] = new FileWriter("src/out2.txt");
            writers[2] = new FileWriter("src/out3.txt");

            for (int i = 0; i < 3; i++) {
                builders[i] = new StringBuilder();
            }

            for (int i = 0; i < n; i++) {
                builders[0].append(numbers[i]).append(" ");
                builders[1].append(upSort[i]).append(" ");
                builders[2].append(downSort[i]).append(" ");
            }

            for (int i = 0; i < 3; i++) {
                writers[i].write(builders[i].toString());
                writers[i].close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
