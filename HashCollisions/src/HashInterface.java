public interface HashInterface {
    Integer get(Integer key);
    void put(Integer key, Integer value);
    int getCollisions();
}
