import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Encoder {
    String maxValue;

    ArrayList<String> words;
    ArrayList<Integer> numbers;

    int[] countingSorted;

    String[] sortedWords;

    int fileNum;

    public Encoder(File file, int n) {
        fileNum = n;

        readFromFile(file);
        countingSort();
        saveToFile();
    }

    public void countingSort() {
        int[] countArr = new int[translateWordsToNum(maxValue) + 1]; // Defaults to 0
        countingSorted = new int[numbers.size()];

        for (Integer num : numbers) {
            countArr[num] += 1;
        }

        for (int i = 1; i < countArr.length - 1; i ++) {
            countArr[i + 1] += countArr[i];
        }

        for (Integer place : numbers) {
            int num = countArr[place];

            countingSorted[num - 1] = place;
            countArr[place] --;
        }
    }

    private int translateWordsToNum(String word) {
        int[] wordNums = new int[3];

        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i ++) {
            char letter = word.charAt(i);
            int value = letter - 96;

            wordNums[i] = value;
        }

        return (wordNums[0] * 10000) + (wordNums[1] * 100) + wordNums[2];
    }

    private String translateNumToWord(int num) {
        char[] numLetter = new char[3];

        String numString = Integer.toString(num);
        sortedWords = new String[countingSorted.length];

        for (int i = 0; i < 3; i ++) {
            numLetter[i] = ' ';
        }

        num += 1;

        if (num > 100000 && num < 1000000) {
            int a = (Integer.parseInt(numString.substring(0, 2)));
            int b = (Integer.parseInt(numString.substring(2, 4)));
            int c = (Integer.parseInt(numString.substring(4, 6)));

            numLetter[0] = (char) (a + 96);
            numLetter[1] = (char) (b + 96);
            numLetter[2] = (char) (c + 96);
        }
        if (num > 10000 && num < 100000){
            int a = (Integer.parseInt(numString.substring(0, 1)));
            int b = (Integer.parseInt(numString.substring(1, 3)));
            int c = (Integer.parseInt(numString.substring(3, 5)));

            numLetter[0] = (char) (a + 96);
            numLetter[1] = (char) (b + 96);
            numLetter[2] = (char) (c + 96);
        }
        if (num > 1000 && num < 10000) {
            int a = (Integer.parseInt(numString.substring(0, 2)));
            int b = (Integer.parseInt(numString.substring(2, 4)));

            numLetter[0] = (char) (a + 96);
            numLetter[1] = (char) (b + 96);
        }
        if (num > 100 && num < 1000) {
            int a = (Integer.parseInt(numString.substring(0, 1)));
            int b = (Integer.parseInt(numString.substring(1, 3)));

            numLetter[0] = (char) (a + 96);
            numLetter[1] = (char) (b + 96);
        }
        if (num > 10 && num < 100) {
            int a = (Integer.parseInt(numString.substring(0, 2)));

            numLetter[0] = (char) (a + 96);
        }
        if (num > 0 && num < 10) {
            int a = (Integer.parseInt(numString.substring(0, 1)));

            numLetter[0] = (char) (a + 96);
        }

        return String.valueOf(numLetter);
    }

    public void readFromFile(File file) {
        words = new ArrayList<>();
        numbers = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            sc.skip("maxValue=");
            maxValue = sc.next();

            while (sc.hasNext()) {
                String nextWord = sc.next();

                words.add(nextWord);
                numbers.add(translateWordsToNum(nextWord));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try {
            FileWriter writer = new FileWriter("src/out_abc" + fileNum + ".txt");
            StringBuilder builder = new StringBuilder();

            for (Integer number : countingSorted) {
                String word = translateNumToWord(number);

                word = word.replace('`', ' ');
                builder.append(word).append(" ");

            }
            writer.write(builder.toString());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}