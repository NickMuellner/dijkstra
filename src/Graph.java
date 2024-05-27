import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    Map<String, Node> nodes = new HashMap<>();

    void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                String prefix = line.split(":")[0];
                List<String> data = List.of(line.split("\""));
                for(int i = 3; i<data.size(); i+=2) {
                    Node firstNode = nodes.getOrDefault(data.get(i-2), new Node());
                    Node secondNode = nodes.getOrDefault(data.get(i), new Node());
                    Edge firstEdge = new Edge(prefix, Integer.parseInt(data.get(i-1).trim()), secondNode);
                    Edge secondEdge = new Edge(prefix, Integer.parseInt(data.get(i-1).trim()), firstNode);
                    firstNode.edgeList.add(firstEdge);
                    secondNode.edgeList.add(secondEdge);
                    nodes.putIfAbsent(data.get(i-2), firstNode);
                    nodes.putIfAbsent(data.get(i-2), secondNode);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
