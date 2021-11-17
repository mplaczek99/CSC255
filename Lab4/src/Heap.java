import java.util.ArrayList;

public class Heap {
    private ArrayList<Integer> heapVector;

    public Heap() {
        heapVector = new ArrayList<>();
    }

    public Heap(final Heap otherHeap) {
        heapVector = new ArrayList<>();

        for (int i = 0; i < otherHeap.size(); i ++) {
            heapVector.set(i, otherHeap.getElement(i));
        }
    }

    public void insert(final Integer element) {
        // if (heapVector.size() - 1 >= size()) {

        // }

        int newPos = percolateUp(element);

        heapVector.add(newPos, element);
    }

    public void deleteMin() {
        // calls percolateDown()
    }

    public int size() {
        return heapVector.size();
    }

    public final Integer getElement(final int index) {
        return heapVector.get(index);
    }

    private int percolateUp(final Integer item) {
        int hole = size();

        while (hole > 1 && item < getElement(hole / 2)) {
            heapVector.set(hole, getElement(hole / 2));
            hole /= 2;
        }
        return hole;
    }

    private void percolateDown() {
        // percolating down
    }
}
