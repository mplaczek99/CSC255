//import java.math.BigInteger;
//import java.util.Scanner;
//
//public class RSACodec {
//    private final RSAAlgorithm rsaAlgorithm;
//
//    public RSACodec(BigInteger p, BigInteger q, BigInteger e) {
//        rsaAlgorithm = new RSAAlgorithm(p, q, e); // probably can use super() instead
//    }
//
//    /**
//    * When encrypting:
//    *  - a data block is read from the input stream and converted to the plaintext number
//    *  - the plaintext number is encrypted to the ciphertext number (via RSA algorithm)
//    *  - and the ciphertext is written to the output stream as one line containing
//    *  - two hexadecimal strings
//    * the block length
//    * the ciphertext number
//    */
//    public void encryptStream(Scanner fin, Scanner fout) {
//        int blockMaxLength = 69; // TODO Calculate maximum chunk length (originally ???)
//
//    }
//
//    /**
//     * When decrypting:
//     *  - two hexadecimal strings are read from the input stream and converted to the block length L and the ciphertext
//     *      number
//     *  - the ciphertext number is decrypted to the plaintext number (via RSA algorithm)
//     *  - right L bytes of the plaintext number are written to the output stream
//     */
//    public void decryptStream(Scanner fin, Scanner fout) {
//        // Define and initialise required variables
//    }
//}
