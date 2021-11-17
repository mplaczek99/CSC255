import java.util.ArrayList;
import java.util.Arrays;

public class HuffmanAlgorithm {
    private ArrayList<HuffmanTree> huffmanTree;
    private int[] frequencyTable;
    private String [] encodingTable;

    private boolean lessComparator(HuffmanTree left, HuffmanTree right) {
        // return left.getFrequency() > right.getFrequency() || (left.getFrequency() == right.getFrequency() && left.getInfo() > right.getInfo());
        return false;
    }

    public HuffmanAlgorithm() {
        frequencyTable = new int[256]; // may be needed
        encodingTable = new String[256]; // may be needed
        huffmanTree = null; // may be needed
    }

    public void countFrequency(byte originalByte) {
        /*
        TODO Increment the frequency count
        Depending on the language, the first increment
        may need a special handling
        */

        frequencyTable[originalByte + 128]++; // meant to change the range from -128-127 to 0-255
    }

    public void finishFrequencyTable() {
        // TODO Just debug output

        printFrequencyTable(); // Debug output
    }

    public void buildHuffmanTree() {
        // TODO  Implement the function body that builds huffmanTree
        huffmanTree = new ArrayList<>();

        for (int i = 0; i < frequencyTable.length; i ++) {
            if (frequencyTable[i] != 0) {
                huffmanTree.add(new HuffmanTree((byte) (i - 128), frequencyTable[i]));
            }
        }

        // sort the tree by frequency
        for (int i = 0; i < huffmanTree.size() - 1; i ++) {
            for (int j = i + 1; j < huffmanTree.size(); j ++) {
                if (huffmanTree.get(i).getFrequency() > huffmanTree.get(j).getFrequency()) { // swap
                    HuffmanTree temp = huffmanTree.get(i);
                    huffmanTree.set(i, huffmanTree.get(j));
                    huffmanTree.set(j, temp);
                }
            }
        }

        System.out.println("After Sorting");

        for (HuffmanTree tree : huffmanTree) {
            byte num = tree.getInfo();
            System.out.println((char) (num) + " ");
        }

        // loop (until the size is 1) to create a new tree with the lowest 2 frequencies, dont forget to remove the used trees, and add the new one
        // where it should be (find a way... good luck dipshit, tomorrows problem for you, not mine for now)

        // huffmanTree may need to be created and old table deleted, depending
        // on the language

        // delete huffmanTree; // may be needed
        // huffmanTree = new tree; // may be needed

        // HuffmanQueue huffmanQueue; // or heap

        // Fill a priority queue or a heap.
        // Depending on your language, you may need to construct
        // the queue as HuffmanQueue() or as HuffmanQueue(lessComparator).
        // See the assignment for details.

        // printQueue(huffmanQueue) // or heap

        // Build huffmanTree from the huffmanQueue (or heap)
    }

    public void makeEncodingTable() {
        for (int i = 0; i < encodingTable.length; i ++) {
            encodingTable[i] = "";
        }

        for (int i = 0; i < frequencyTable.length; i ++) {
            if (frequencyTable[i] == 0) {
                continue;
            } else {
                encodingTable[i] = "1";
            }
        }
    }

    public String encodeByte(byte originalByte) {
        // TODO Implement the encoding function body
        return ""; // Make the tree first, get info from the tree
    }

    public void decodeByte() {
        // Probably a void-return method... probably

        //TODO Implement the decoding function body
    }

    private void printFrequencyTable() {
        // some nonsense
        System.out.print("printFrequencyTable:\n\n");

        for (int i = 0; i < frequencyTable.length; i ++) {
            if (frequencyTable[i] == 0) {
                continue;
            }

            System.out.print(i + " " + (char) (i - 128) + " {" + frequencyTable[i] + "}\n"); // (i - 128) changes its range back to -128-127
        }
    }

    private void printEncodingTable() {
        // some more nonsense
        System.out.print("printEncodingTable:\n\n");

        for (int i = 0; i < encodingTable.length; i ++) {
            if (encodingTable[i].length() == 0) {
                continue;
            }

            System.out.print(i + " " + (char) (i - 128) + " {" + encodingTable[i] + "}\n");
        }
    }

    private void printFrequency() {
        // some extra nonsense
    }

    private void printQueue() {
        // some over-the-top nonsense
    }
}
