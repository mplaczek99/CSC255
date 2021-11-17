import java.math.BigInteger;
import java.util.ArrayList;

public class RSAObject {
    private final BigInteger p;
    private final BigInteger q;
    private final BigInteger e;
    private final BigInteger r;
    private final BigInteger n;
    private final BigInteger phi;
    private final BigInteger d;
    private final BigInteger s;

    public RSAObject(BigInteger p, BigInteger q, BigInteger e, BigInteger r) { // The reference had us use BigInteger
        // for BigNumber so, I kept that practice

        this.p = p;
        this.q = q;
        this.e = e;
        this.r = r;

        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        d = e.modInverse(phi); // If my math is correct...
        s = r.modPow(d, n);
    }

    public String translateToString(BigInteger s) {
        ArrayList<Integer> nums = new ArrayList<>();
        String sString = s.toString();
        StringBuilder result = new StringBuilder();

        int start = 0;
        for (int end = 1; end < sString.length(); end ++) {
            if (sString.charAt(end) == '0') {
                nums.add(Integer.valueOf(sString.substring(start, end)));
                start = end;
            }
            if (sString.length() - end == 1) {
                nums.add(Integer.valueOf(sString.substring(start, end + 1)));
            }
        }

        for (Integer num : nums) {
            result.append((char) (num + 96)); // Transforms num to ASCII value, then converts it to a char
        }

        return result.toString();
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getR() {
        return r;
    }

    public BigInteger getPhi() {
        return phi;
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getS() {
        return s;
    }
}
