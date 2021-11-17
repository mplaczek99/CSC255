import java.io.IOException;
import java.io.InputStream;

public class HuffmanCodec {
    /*
    This class contains all input/output so HuffmanAlgorithm deals with the actual encoding/decoding.
    Note that the data streams can contain arbitrary bytes so the data has to read and written as raw bytes.
     */

    private HuffmanAlgorithm huffmanAlgorithm;

    private void makeFrequencyTable(InputStream fin) throws IOException {// something goes in here) { // private method
        int length = fin.available();

        for (int i = 0; i < length; i ++) {
            huffmanAlgorithm.countFrequency((byte) fin.read());
        }
        System.out.print("\n");
        huffmanAlgorithm.finishFrequencyTable();
    }

    private void encodeData(InputStream fin) throws IOException {
        int length = fin.available();

        for (int i = 0; i < length; i++) {
            System.out.print(huffmanAlgorithm.encodeByte((byte) fin.read()));
        }
        System.out.print("\n"); // this line is not in pseudocode
    }

    public void encodeStream(InputStream fin) throws IOException {
        makeFrequencyTable(fin);
        huffmanAlgorithm.makeEncodingTable();
        huffmanAlgorithm.buildHuffmanTree();

        // encodeData(fin);
    }

    public HuffmanCodec() {
        huffmanAlgorithm = new HuffmanAlgorithm();
    }
}
