import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable {
    List<Edge> edgeList = new ArrayList<>();
    int distanceFromRoot;
    Node previous;
    boolean isVisited;
    String name;

    public Node(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Node && ((Node)obj).name.equals(name));
    }

    @Override
    public int compareTo(Object o) {
        return distanceFromRoot - ((Node)o).distanceFromRoot;
    }

    public List<Node> getNeighbors(boolean withoutVisited) {
        if(withoutVisited) {
            return edgeList.stream().filter(e -> !e.toNode.isVisited).map(e -> e.toNode).toList();
        } else {
            return edgeList.stream().map(e -> e.toNode).toList();
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public void visit() {
        isVisited = true;
        edgeList.stream()
                .filter(e -> !e.toNode.isVisited)
                .filter(e -> distanceFromRoot + e.weight < e.toNode.distanceFromRoot || e.toNode.distanceFromRoot == 0)
                .forEach(e -> { e.toNode.distanceFromRoot = distanceFromRoot + e.weight; e.toNode.previous = this; });
    }
}
