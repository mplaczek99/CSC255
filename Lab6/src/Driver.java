import java.io.*;
import java.util.Scanner;

public class Driver {
    // The input files may contain any bytes, so default reading methods cannot be used.
    // The bytes have to be read as-is, without any conversion.

    FileOutputStream fout;
    String prefix = "src/InputOutputFiles/";

    public Driver(int numberFile) throws IOException {
        testFile(numberFile);

        System.out.close();
    }

    private void compareFiles(String firstFile, String secondFile) throws IOException {
        InputStream fin1 = new FileInputStream(prefix + firstFile);
        InputStream fin2 = new FileInputStream(prefix + secondFile);

        byte[] buffer1 = new byte[fin1.available()];
        byte[] buffer2 = new byte[fin2.available()];

        int length1 = fin1.read(buffer1);
        int length2 = fin2.read(buffer2);

        System.out.print("Comparing " + firstFile + " and " + secondFile + "\n");

        if (length1 == 0) {
            throw new RuntimeException(firstFile + " is empty"); // Loop will not happen. Even if we make it so, the proper Exception is not thrown
        } else if (length1 != length2) { // The else MAY be unnecessary, but just in case...
            throw new RuntimeException("Sizes of the files are different, file " + firstFile + " has size " + length1 + ", file " + secondFile + " has size " + length2);
        }

        for (int i = 0; i < length1; i++) {
            if (buffer1[i] != buffer2[i]) {
                throw new RuntimeException("Files are different at position " + i + ", file " + firstFile + " has character " + String.valueOf(buffer1[i]) + ", file " + secondFile + " has character " + String.valueOf(buffer2[i]));
            }
        }

        fin1.close();
        fin2.close();
    }

    private void testEncoding(HuffmanCodec huffmanCodec, String inputFile, String encodedFile) throws IOException {
        System.out.print("Encoding " + inputFile + " -> " + encodedFile + "\n");
        InputStream fin = new FileInputStream(prefix + inputFile);
        huffmanCodec.encodeStream(fin);

        // WRITE CUSTOM ENCODING CODE
    }

    private void testDecoding(HuffmanCodec huffmanCodec, String encodedFile, String decodedFile) throws FileNotFoundException {
        Scanner fin = new Scanner(new FileReader(prefix + encodedFile));

        System.out.print("Decoding " + encodedFile + " -> " + decodedFile + "\n");

        // WRITE CUSTOM DECODING CODE HERE7
    }

    private void testFile(int numberFile) throws IOException {
        String debugFile = numberFile + "_debug.txt";
        String inputFile = numberFile + "_in.txt";
        String encodedFile = numberFile + "_encoded.txt";
        String decodedFile = numberFile + "_decoded.txt";

        System.out.print("*** Testing file " + inputFile + ", debug output file " + debugFile + " ***\n");

        HuffmanCodec huffmanCodec = new HuffmanCodec();

        testEncoding(huffmanCodec, inputFile, encodedFile);
        testDecoding(huffmanCodec, encodedFile, decodedFile);
        compareFiles(inputFile, decodedFile);

        System.out.print("\n!!! Files " + inputFile + " and " + decodedFile + " are equal.\n");
    }

    public static void main(String[] args) {
        try {
            /*
            for (int i = 0; i < 9; i ++) {
                new Driver(i);
            }
            */

             new Driver(4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
