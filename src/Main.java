public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.readFile("WienerVerkehrsNetz.txt");
        graph.nodes.forEach((e, f) -> System.out.println(e));
    }
}
