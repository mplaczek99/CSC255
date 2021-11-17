import java.io.File;

public class Driver {
    public static void main(String[] args) {
        new Encoder(new File("src/in_abc10.txt"), 10);
        new Encoder(new File("src/in_abc100.txt"), 100);
    }
}