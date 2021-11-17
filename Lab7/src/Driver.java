import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Driver {
    /**
     * @param rsaObject Does various tests based on rsaObject
     */
    private static void testRSA(RSAObject rsaObject) {
        System.out.println("Given RSA Values:");
        System.out.println("p = " + rsaObject.getP());
        System.out.println("q = " + rsaObject.getQ());
        System.out.println("e = " + rsaObject.getE());
        System.out.println("r = " + rsaObject.getR());
        System.out.println("Find n, d, s\n");

        System.out.println("phi = " + rsaObject.getPhi());
        System.out.println("d = " + rsaObject.getD());
        System.out.println("n = " + rsaObject.getN() + "\n");

        System.out.print("decrypted number s = ");
        try {
            System.out.println(rsaObject.getS());
            System.out.println("decrypted text s_str = " + rsaObject.translateToString(rsaObject.getS()));
            System.out.println("OK\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param values p, q, e, and r. In that order!
     * @return A new instance of an RSAObject
     */
    private static RSAObject createRSA(BigInteger[] values) {
        return new RSAObject(values[0], values[1], values[2], values[3]);
    }

    /**
     * Reads the p, q, e, and r values from the given file
     */
    private static BigInteger[] readFromFile(FileReader fileReader) {
        Scanner inFile = new Scanner(fileReader);
        BigInteger[] values = new BigInteger[4];

        while (inFile.hasNext()) {
            switch (inFile.next()) {
                case "p":
                    values[0] = inFile.nextBigInteger();
                    break;
                case "q":
                    values[1] = inFile.nextBigInteger();
                    break;
                case "e":
                    values[2] = inFile.nextBigInteger();
                    break;
                case "encrypted":
                    inFile.findInLine("r=");
                    values[3] = inFile.nextBigInteger();
                default:
                    inFile.nextLine();
            }
        }

        inFile.close();

        return values;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Solve for Michael Placzek");

        RSAObject rsaObject = createRSA(readFromFile(new FileReader("src/Lab7_to_student_PlaczekMichael.txt")));
        testRSA(rsaObject);
    }
}
