public class HuffmanTree<T> {
    private byte info;
    private int frequency;
    private HuffmanTree<T> left;
    private HuffmanTree<T> right;

    public HuffmanTree(byte info, int frequency) {
        this.info = info;
        this.frequency = frequency;
        left = null;
        right = null;
    }

    public HuffmanTree(HuffmanTree<T> first, HuffmanTree<T> second) {
        frequency = first.getFrequency() + second.getFrequency();
        left = first.getFrequency() <= second.getFrequency() ? first : second;
        right = first.getFrequency() <= second.getFrequency() ? second : first;
    }

    // Additional constructor here

    public int getFrequency() {
        return frequency;
    }

    public byte getInfo() {
        return info;
    }

    public HuffmanTree<T> getLeft() {
        return left;
    }

    public HuffmanTree<T> getRight() {
        return right;
    }

    public boolean isTerminal() {
        return left == null && right == null;
    }
}
