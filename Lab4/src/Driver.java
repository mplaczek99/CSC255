import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    FileOutputStream fout; // declare and create an output stream
    ArrayList<Integer> inputData; // all keys from the input file

    // Print data and heap
    public void printHeap(final String description, final Heap heap) {
        System.out.print(description + "\n");
        System.out.print("[" + heap.size() + "]");

        for (int i = 0; i < heap.size(); i++) {
            System.out.print(" " + heap.getElement(i));
        }
        System.out.print("\n");
    }

    // Consider heap elements ordered if first <= second or second is beyond heap size
    public boolean areHeapElementsOrdered(final Heap heap, final int i, final int j) {
        return i >= heap.size() || j >= heap.size() || heap.getElement(i) <= heap.getElement(j); // Edit this line | Error here?
    }

    // Returns stringized element or "none" if beyond heap size
    public String heapElement(final Heap heap, final int i) {
        return i < heap.size() ? heap.getElement(i).toString() : "none";
    }

    // Check that it is really a heap, throw an exception otherwise
    public void checkHeap(final Heap heap) {
        for (int i = 0; i < heap.size(); i++) {
            if (areHeapElementsOrdered(heap, i, i * 2 + 1) && areHeapElementsOrdered(heap, i, i * 2 + 2)) {
                continue;
            } else {
                // else is added
                printHeap("Corrupted", heap);

                System.out.print("Error: heap violation at index " + i + ", heap[" + i + "] = " + heapElement(heap, i) + ", heap[" + (i * 2 + 1) + "] = " + heapElement(heap, i * 2 + 1) + ", heap[" + (i * 2 + 2) + "] = " + heapElement(heap, i * 2 + 2) + "\n");
                throw new RuntimeException("Not a heap");
            }
        }
    }

    // Insert one element, check, and print
    public void insertOne(final Heap heap, int element) {
        System.out.print("Insert " + element + "\n");

        heap.insert(element);
        checkHeap(heap);
    }

    // Delete minimum element, check, and print
    public void deleteOne(final Heap heap) {
        System.out.print("Delete " + heap.getElement(0) + "\n");

        heap.deleteMin();
        checkHeap(heap);
    }

    // Test heap functions
    // - insert all input elements
    // - insert 31 and 14
    // - delete all min elements
    public void testData() {
        Heap heap = new Heap();

        for (Integer key : inputData) {
            heap.insert(key);
            checkHeap(heap);
        }
        printHeap("Heap", heap);

        insertOne(heap, 31);
        printHeap("Heap after insert 31", heap);

        insertOne(heap, 14);
        printHeap("Heap after insert 14", heap);

        while (heap.size() > 0) {
            deleteOne(heap);
            printHeap("Heap after deleteOne", heap);
        }
    }

    // Read data from the Integers from the input file
    public void readData(final String inputFile) throws FileNotFoundException {
        Scanner fin = new Scanner(new File(inputFile));
        Integer key;

        inputData = new ArrayList<>();

        System.out.println("Input data");

        while (fin.hasNext()) {
            key = fin.nextInt();

            inputData.add(key);
        }
        System.out.println(inputData);
        System.out.print("\n");

        fin.close();
    }

    // Test the input file, print result to the output file
    public void testFile(final String inputFile, final String outputFile) throws IOException {
        // PrintStream original = new PrintStream(System.out);
        // fout = new FileOutputStream(outputFile);
        // PrintStream ps = new PrintStream(fout);

        // System.setOut(ps);

        readData(inputFile);
        testData();

        // fout.close();
    }

    public Driver(int index) {
        try {
            final String suffix = index + ".txt";
            testFile("src/in" + suffix, "src/out" + suffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Driver(1);
        // new Driver(2);
        // new Driver(3);
        // new Driver(4);
    }
}
