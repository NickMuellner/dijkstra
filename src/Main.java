import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.readFile("WienerVerkehrsNetz.txt");
        //graph.nodes.forEach((e, f) -> System.out.println(f));
        System.out.println(graph.dijkstra("Stephansplatz", "Ottakring").stream().map(e -> e.name).collect(Collectors.joining(" - ")));
    }
}
