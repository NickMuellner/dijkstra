import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        if(args.length == 3){
            Graph graph = new Graph();
            graph.readFile(args[0]);
            //graph.nodes.forEach((e, f) -> System.out.println(f));
            System.out.println(graph.dijkstra(args[1], args[2]).stream().map(e -> e.name).collect(Collectors.joining(" - ")));
            System.out.println("Journey length in minutes: " + graph.getEndNodeDistance());
        }else{
            System.err.println("Invalid usage!");
        }
    }
}
