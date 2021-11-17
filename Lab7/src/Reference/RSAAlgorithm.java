//
//import java.math.BigInteger;
//
//public class RSAAlgorithm {
//    private final BigInteger p;
//    private final BigInteger q;
//    private final BigInteger e;
//    private final BigInteger n;
//    private BigInteger d;
//
//    public RSAAlgorithm(BigInteger p, BigInteger q, BigInteger e) {
//        this.p = p;
//        this.q = q;
//        this.e = e;
//
//        n = p.multiply(q);
//
//        System.out.println("--- RSAAlgorithm" + "\n p " + p + "\n q " + q + "\n e " + e + "\n n " + n + "\n");
//    }
//
//    public BigInteger encrypt(BigInteger plainTextNumber) {
//        BigInteger cipherText = plainTextNumber.pow(e.intValueExact()).mod(n);
//        // TODO Calculate cipherTextNumber
//
//        System.out.print(cipherText);
//        System.out.print("RSAAlgorithm::encrypt " + plainTextNumber.byteValueExact() + " -> " + cipherText.byteValueExact());
//
//        return cipherText;
//    }
//
//    public BigInteger decrypt(BigInteger cipherTextNumber) {
//        // TODO Calculate plainTextNumber
//
//        // debugStream...
//
//        return null;
//    }
//
//    public BigInteger getN() {
//        return n;
//    }
//}
