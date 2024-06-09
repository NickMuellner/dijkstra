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
            System.out.print(edges.getFirst().line + ": ");
            for (int i = 0; i < nodes.size()-1; i++) {
                if(!currentLine.equals(edges.get(i).line)) {
                    currentLine = edges.get(i).line;
                    System.out.print(currentLine + ": ");
                }
                System.out.print(nodes.get(i) + " -(" + edges.get(i).weight + ")- ");
            }
            System.out.print(nodes.getLast());
            System.out.println("Journey length in minutes: " + graph.getEndNodeDistance());
        } else {
            System.err.println("Invalid usage!");
        }
    }
}
