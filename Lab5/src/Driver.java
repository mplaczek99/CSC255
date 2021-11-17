import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    FileOutputStream fout; // declare and create an output stream
    ArrayList<Integer> data = new ArrayList<>(); // all keys from the input file

    public Driver(int number) {
        String suffix = "_edges.txt";

        try {
            testFile("src/in" + number + suffix, "src/out" + number + suffix);
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }

    public void testFile(final String inputFilename, final String outputFilename) throws IOException {
        fout = new FileOutputStream(outputFilename);
        PrintStream ps = new PrintStream(fout);
        System.setOut(ps);

        readFile(inputFilename); // populate data with what's in the file

        Graph graph = new Graph(numVertices());

        for (int x = 0, y = 1, z = 2; z < data.size(); x+=3, y+=3, z+=3) {
            graph.addEdge(data.get(x), data.get(y), data.get(z));
        }
        graph.printGraph("Graph", graph.allEdges);
        System.out.print("\n");
        graph.kruskalMST();

        fout.close();

        System.out.println("Done");
    }

    private void readFile(String fileName) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileName));

        file.nextLine();

        while (file.hasNext()) {
            data.add(file.nextInt());
        }
    }

    private int numVertices() {
        int number = 0;

        for (int i = 1, j = 1; j < data.size(); i += 3, j += 3) {
            if (number < data.get(i)) {
                number = data.get(i);
            }
            if (number < data.get(j)) {
                number = data.get(j);
            }
        }

        return number + 1;
    }

    public static void main(String[] args) {
        new Driver(2);
        new Driver(3);
    }
}
