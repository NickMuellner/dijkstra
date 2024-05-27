public class Edge {
    Node nodeA;
    Node nodeB;
    int weight;
    String line;

    public Edge(String line, int weight, Node secondNode) {
        this.line = line;
        this.weight = weight;
    }
}
