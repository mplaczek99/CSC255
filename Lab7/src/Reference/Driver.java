//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.math.BigInteger;
//import java.util.Scanner;
//
//public class Driver {
//    public static void testEncrypting(RSACodec rsaCodec, String plainFile, String encryptedFile)
//            throws FileNotFoundException {
//        // binary inputStream (plainFile)
//        Scanner fin = new Scanner(new FileReader(plainFile));
//        Scanner fout = new Scanner(new FileReader(encryptedFile));
//
//        System.out.println("*** Encrypting " + plainFile + ", size " + new File(encryptedFile).length() + " -> "
//                + encryptedFile + " ***"); // size might be wrong
//
//        rsaCodec.encryptStream(fin, fout);
//
//        fin.close();
//        fout.close();
//    }
//
//    public static void testFile(int fileNumber) throws FileNotFoundException {
//        String numberString = Integer.toString(fileNumber);
//        String prefix = "src/InputFiles/";
//        String debugFile = prefix + numberString + "_debug.txt";
//        String keyMaterialFile = prefix + numberString + "_keymat.txt";
//        String plainFile = prefix + numberString + "_in.dat";
//        String encryptedFile = prefix + numberString + "_encrypted.txt";
//        String decryptedFile = prefix + numberString + "_decrypted.dat";
//
//        // debugStream.open(debugFile);
//
//        System.out.println("*** Testing input file " + plainFile + ", debug output file " + debugFile + " ***");
//
//        RSACodec rsaCodec = makeRSACodecFromFile(keyMaterialFile); // keyFile
//
//        testEncrypting(rsaCodec, plainFile, encryptedFile);
//
//         /*
//        testDecrypting(rsaCodec, encryptedFile, decryptedFile);
//
//        FileComparator.compareFiles(plainFile, decryptedFile); // HAS TO THROW IF NOT EQUAL
//
//        debugStream << ">>> Files " << plainFile << " and " << decryptedFile << " are equal.\n"
//        debugStream.FLUSH()
//
//        debugStream.close()
//        */
//
//        System.out.println("\nOK\n");
//        System.out.flush();
//    }
//
//    private static RSACodec makeRSACodecFromFile(String keyMaterialFile) throws FileNotFoundException {
//        Scanner fin = new Scanner(new FileReader(keyMaterialFile));
//        BigInteger p = null, q = null, e = null;
//
//        while (fin.hasNext()) {
//            switch (fin.next()) {
//                case "p":
//                    p = fin.nextBigInteger();
//                    break;
//                case "q":
//                    q = fin.nextBigInteger();
//                    break;
//                case "e":
//                    e = fin.nextBigInteger();
//                    break;
//            }
//        }
//
//        fin.close();
//
//        if (p == null) {
//            throw new RuntimeException("Missing or invalid p value in key file " + keyMaterialFile);
//        }
//        if (q == null) {
//            throw new RuntimeException("Missing or invalid q value in key file " + keyMaterialFile);
//        }
//        if (e == null) {
//            throw new RuntimeException("Missing or invalid e value in key file " + keyMaterialFile);
//        }
//
//        return new RSACodec(p, q, e);
//    }
//
//    public static void main(String[] args) throws FileNotFoundException {
//        /*
//        for (int i = 1; i <= 3; i ++) {
//            testFile(i);
//        }
//         */
//        testFile(1);
//    }
//}
