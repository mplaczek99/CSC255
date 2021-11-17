import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Counter {
    int k;
    int n;

    ArrayList<Integer> numbers;
    int[] countingSorted;
    int[] selectionSorted;

    int fileNum;

    public Counter(File file, int fileNum) {
        this.fileNum = fileNum;

        readFromFile(file);
        countingSort();
        selectionSort();
        saveToFile();
    }

    public void countingSort() {
        int[] countArr = new int[k]; // Defaults to 0
        countingSorted = new int[n];

        for (Integer num : numbers) {
            countArr[num] += 1;
        }

        for (int i = 1; i < k - 1; i ++) {
            countArr[i + 1] += countArr[i];
        }

        for (Integer place : numbers) {
            int num = countArr[place];

            countingSorted[num - 1] = place;
            countArr[place] --;
        }
    }

    public void selectionSort() {
        selectionSorted = new int[n];
        for (int i = 0; i < n; i ++) {
            selectionSorted[i] = numbers.get(i);
        }

        for (int i = 0; i < n - 1; i ++) {
            int min = i;

            for (int j = i + 1; j < n; j ++) {
                if (selectionSorted[j] < selectionSorted[min]) {
                    min = j;
                }
            }

            // Swap the found element with the first element of loop
            int temp = selectionSorted[min];
            selectionSorted[min] = selectionSorted[i];
            selectionSorted[i] = temp;
        }
    }

    public void readFromFile(File file) {
        numbers = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            k = sc.nextInt();

            while (sc.hasNext()) {
                numbers.add(sc.nextInt());
            }

            n = numbers.size();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter("src/out" + fileNum + "a.txt");
            String isFound;
            StringBuilder builder = new StringBuilder();

            for (Integer number : countingSorted) {
                builder.append(number).append(" ");
            }
            writer.write(builder.toString());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("src/out" + fileNum + "b.txt");
            String isFound;
            StringBuilder builder = new StringBuilder();

            for (Integer number : selectionSorted) {
                builder.append(number).append(" ");
            }
            writer.write(builder.toString());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
