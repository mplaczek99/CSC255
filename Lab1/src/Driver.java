import java.io.File;

public class Driver {
    public static void main(String[] args) {
        // Get all files and pass to SumOfK
        for (int i = 1; i < 6; i ++) {
            new SumOfK(new File("src/in" + i + ".txt"), i);
        }
    }
}
