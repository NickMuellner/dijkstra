public class Edge {
    Node toNode;
    int weight;
    String line;

    public Edge(String line, int weight, Node secondNode) {
        this.line = line;
        this.weight = weight;
        this.toNode = secondNode;
    }

    @Override
    public String toString() {
        return line + ": " + toNode.toString() + " (" + weight + ")";
    }
}
