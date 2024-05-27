import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
    Map<String, Node> nodes = new HashMap<>();

    void readFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while (line != null) {
                String prefix = line.split(":")[0];
                List<String> data = List.of(line.split("\""));
                for(int i = 3; i<data.size(); i+=2) {
                    Node firstNode = nodes.getOrDefault(data.get(i-2), new Node(data.get(i-2)));
                    Node secondNode = nodes.getOrDefault(data.get(i), new Node(data.get(i)));
                    Edge firstEdge = new Edge(prefix, Integer.parseInt(data.get(i-1).trim()), secondNode);
                    Edge secondEdge = new Edge(prefix, Integer.parseInt(data.get(i-1).trim()), firstNode);
                    firstNode.edgeList.add(firstEdge);
                    secondNode.edgeList.add(secondEdge);
                    nodes.putIfAbsent(data.get(i-2), firstNode);
                    nodes.putIfAbsent(data.get(i), secondNode);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    List<Node> dijkstra(String start, String end) {
        if(!nodes.containsKey(start)) {
            throw new IllegalArgumentException("Start node not found: " + start);
        }
        Node startNode = nodes.get(start);
        startNode.isVisited = true;
        startNode.edgeList.forEach(e -> e.toNode.distanceFromRoot = e.weight);
        startNode.edgeList.forEach(e -> e.toNode.previous = startNode);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.addAll(startNode.getNeighbors(true));
        while(!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();
            System.out.println(currentNode);
            currentNode.visit();
            currentNode.getNeighbors(true).stream().filter(e -> !priorityQueue.contains(e)).forEach(priorityQueue::add);
        }
        Node previous = nodes.get(end);
        List<Node> route = new ArrayList<>();
        while (previous != null) {
            route.add(previous);
            previous = previous.previous;
        }
        return route.reversed();
    }
}