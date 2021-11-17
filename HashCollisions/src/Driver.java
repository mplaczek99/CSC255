import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {
    final int tableSize = 191; // hash table size
    final int doubleFactor = 181; // factor R to be used in double hashing

    FileOutputStream fout; // declare and create an output stream
    ArrayList<Integer> data = new ArrayList<>(); // all keys from the input file

    public Driver(int number) {
        try {
            testFile("src/in" + number + ".txt", "src/out" + number + ".txt");
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }

    public void testKeyValue(HashInterface hashTable, final Integer key, final Integer value) throws RuntimeException {
        final int previousCollisions = hashTable.getCollisions();

        hashTable.put(key, value);

        final Integer retrievedValue = hashTable.get(key);
        final String retrievedText = retrievedValue.toString(); // email / discord someone about this

        System.out.print(key + " : " + value + " -> " + retrievedText + ", collisions " + (hashTable.getCollisions() - previousCollisions) + "\n");

        if (!retrievedValue.equals(value)) {
            System.out.print("Retrieved value " + retrievedText + " does not match stored value"  + value + " for key " + key + "\n");

            throw new RuntimeException("value mismatch");
        }
    }

    public void testInputKey(final Integer key, HashInterface lph, HashInterface qph, HashInterface dhph) { // Removed other hashing
        final Integer value = key * 2;

        testKeyValue(lph, key, value);
        testKeyValue(qph, key, value);
        testKeyValue(dhph, key, value);

        System.out.print("\n");
    }

    public void testData(final String description) {
        System.out.print("*** " + description + " Start ***" + "\n\n");

        LinearProbingHash lph = new LinearProbingHash(tableSize);
        QuadraticProbingHash qph = new QuadraticProbingHash(tableSize);
        DoubleProbingHash dhph = new DoubleProbingHash(tableSize, doubleFactor);

        for (Integer key : data) {
            testInputKey(key, lph, qph, dhph);
        }

        System.out.print("Linear    " + lph.getCollisions() + " collisions" + "\n");
        System.out.print("Quadratic " + qph.getCollisions() + " collisions" + "\n");
        System.out.print("Double    " + dhph.getCollisions() + " collisions" + "\n");

        System.out.print("\n" + "*** " + description + " End ***" + "\n\n");
    }

    public void readData(final String inputFile) throws FileNotFoundException {
        Scanner scanner= new Scanner(new File(inputFile ));

        while (scanner.hasNext()) {
            data.add(scanner.nextInt());
        }

        scanner.close();
    }

    public void testFile(final String inputFilename, final String outputFilename) throws IOException {
        PrintStream original = new PrintStream(System.out);
        fout = new FileOutputStream(outputFilename);
        PrintStream ps = new PrintStream(fout);

        readData(inputFilename);

        System.out.println("Input file: " + inputFilename + ", output file: " + outputFilename);

        System.setOut(ps);
        testData("Random Order");

        Collections.sort(data);
        testData("Ascending Order");

        data.sort(Collections.reverseOrder());
        testData("Descending Order");

        fout.close();

        System.setOut(original);
        System.out.println("Done");
    }

    public static void main(String[] args) {
        new Driver(150);
        new Driver(160);
        new Driver(170);
    }
}
