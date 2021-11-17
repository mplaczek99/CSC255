public class DoubleProbingHash implements HashInterface {
    Record[] table;
    int collisions;
    int doubleFactor;

    private static class Record {
        Integer key;
        Integer value;

        public Record(final Integer key1, final Integer value1) {
            key = key1;
            value = value1;
        }
    }

    public DoubleProbingHash(int initialSize, int factor) {
        table = new Record[initialSize];

        collisions = 0;
        doubleFactor = factor;
    }

    @Override
    public Integer get(final Integer key) {
        final int index = lookUp(key);

        if (index > table.length) {
            return null;
        }

        Record p = table[index];

        return p.value;
    }

    @Override
    public void put(final Integer key, final Integer value) throws RuntimeException {
        final int index = lookUp(key);

        if (index > table.length) {
            throw new RuntimeException("Table is full");
        }

        Record p = table[index];

        if (p == null) {
            table[index] = new Record(key, value);
        } else {
            p.value = value;
        }
    }

    @Override
    public final int getCollisions() {
        return collisions;
    }

    private static int hash(final Integer key) {
        return (key >> 8) | ((key&0xff)<<16);
    }

    private int hash2(final Integer key) {
        return doubleFactor - (hash(key) % doubleFactor);
    }

    private int hashIndex(final Integer key) {
        final int index = hash(key);

        return index % table.length;
    }

    private int lookUp(Integer key) {
        final int startIndex = hashIndex(key);
        int index = startIndex;

        int i = 0;
        while (true) {
            final Record p = table[index];

            if (p == null || p.key.equals(key)) {
                return index;
            }

            collisions ++;
            i ++;

            index = (i * hash2(key) + startIndex) % table.length;

            if (index == startIndex) {
                return table.length + 1;
            }
        }
    }
}
