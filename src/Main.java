import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if(args.length == 3){
            Graph graph = new Graph();
            graph.readFile(args[0]);
            //graph.nodes.forEach((e, f) -> System.out.println(f));
            List<Node> nodes = graph.dijkstra(args[1], args[2]);
            List<Edge> edges = graph.getEdgesFromNodes(nodes);
            String currentLine = edges.getFirst().line;
            for (int i = 0; i < nodes.size()-1; i++) {
                if(!currentLine.equals(edges.get(i).line)) {
                    System.out.println("Change from " + currentLine + " to " + edges.get(i).line);
                    currentLine = edges.get(i).line;
                }
                System.out.println(nodes.get(i));
                System.out.println(edges.get(i).line + ": " + edges.get(i).weight);
            }
            System.out.println(nodes.getLast());
            System.out.println(System.lineSeparator() + "Route:");
            System.out.println(nodes.stream().map(e -> e.name).collect(Collectors.joining(" - ")));
            System.out.println("Journey length in minutes: " + graph.getEndNodeDistance());
        } else {
            System.err.println("Invalid usage!");
        }
    }
}
