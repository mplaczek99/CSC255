import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SumOfK {
    File file;
    ArrayList<Integer> numbers, sortedNumbers;
    int answer;
    boolean found = false;
    int num1, num2;

    public SumOfK(File file, int fileNum) {
        this.file = file;
        numbers = new ArrayList<>();
        sortedNumbers = new ArrayList<>();

        readFromFile();
        saveToFile(fileNum);
    }

    public void readFromFile() {
        try {
            Scanner sc = new Scanner(file);

            sc.nextLine();// Skip a line, by not paying attention to it

            answer = sc.nextInt();
            while (sc.hasNext()) {
                numbers.add(sc.nextInt()); // Adds line from file as an ArrayList (numbers)
            }

            sortedNumbers = (ArrayList<Integer>) numbers.clone();
            // Sorting algorithm
            heapSort();

            // The counting algorithm

//                for (int i = 0; i < numbers.size() - 1; i ++) {
//                    num1 = numbers.get(i);
//                    for (int j = i; j < numbers.size(); j++) {
//                        num2 = numbers.get(j);
//                        if (num1 + num2 == answer) {
//                            found = true;
//                            return;
//                            /*
//                             Does the use of a return statement (which abruptly ends the loop by ending the method, make my algorithm not O(N)?
//                             Once it finds num1 + num2 = answer, it just terminates the loop, therefore, not checking the others...
//                             Is this a memory leak also? Or is the Scanner automatically closed?
//                            */
//                        }
//                        if (num1 + num1 == answer) {
//                            num2 = num1;
//                            found = true;
//                            return;
//                        }
//                    }
//                }

            num1 = numbers.get(0);

            for (int i = 1; i < numbers.size(); i ++) {
                num2 = numbers.get(i);

                if (num1 * 2 == answer) {
                    num2 = num1;
                    found = true;
                    return;
                }

                if (num1 + num2 == answer) {
                    found = true;
                    return;
                }

                if (i < numbers.size()) {
                    num1 = num2;
                }
            }

            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(int fileNum) {
        try {
            FileWriter writer = new FileWriter("src/out" + fileNum + ".txt");
            String isFound;
            StringBuilder numbersWithSpaces = new StringBuilder();

            if (found) {
                isFound = "Yes";
            } else {
                isFound = "No";
            }

            for (Integer number : numbers) {
                numbersWithSpaces.append(number).append(" ");
            }
            numbersWithSpaces.append("\n");

            for (Integer number : sortedNumbers) {
                numbersWithSpaces.append(number).append(" ");
            }

            writer.write(answer + "\n" + numbersWithSpaces + "\n" + isFound);

            if (found) {
                writer.write("\n" + num1 + "+" + num2  + "=" + answer);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void heapSort() { // I got confused by the Wiki. I watched a YouTube video to understand HeapSort better
        makeHeap();
        int heapSize =sortedNumbers.size() - 1;

        for (int i = heapSize; i > 0; i --) {
            swap(0, i);
            heapSize --;
            heapify(0, heapSize);
        }
    }

    private void makeHeap() {
        for (int i = (sortedNumbers.size() - 1) / 2; i >= 0; i --) {
            heapify(i, sortedNumbers.size() - 1);
        }
    }

    private void heapify(int index, int size) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int max;

        // Check the left value
        if (left <= size && sortedNumbers.get(left) > sortedNumbers.get(index)) {
            max = left;
        } else {
            max = index;
        }

        // Check the right value
        if (right <= size && sortedNumbers.get(right) > sortedNumbers.get(max)) {
            max = right;
        }

        // Special case
        if (max != index) {
            swap(index, max);
            heapify(max, size);
        }
    }

    private void swap(int original, int number) {
        int temp = sortedNumbers.get(original);
        sortedNumbers.set(original, sortedNumbers.get(number));
        sortedNumbers.set(number, temp);
    }
}
