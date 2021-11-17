import java.io.File;

public class Driver {
    public static void main(String[] args) {
        new Counter(new File("src/in10.txt"), 10);
        new Counter(new File("src/in100.txt"), 100);
    }
}
