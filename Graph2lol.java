package main;

import java.util.*;

public class Graph2lol {
    class GraphNode {
        int key;
        String value;
        Set<GraphNode> neighbors;

        public GraphNode(int k, String v) {
            this.key = k;
            this.value = v;
            this.neighbors = new HashSet<>();
        }
        public int getKey() {
            return key;
        }
        public String getValue() {
            return value; }

    }

    List<GraphNode> nodes;
    HashMap<String, Set<GraphNode>> traversallist;
    HashMap<Integer, GraphNode> nodemap;


    public Graph2lol() {
        this.traversallist = new HashMap<>();
        this.nodemap = new HashMap<>();
    }
    public void addWord(int p, String v) {
        GraphNode moo = new GraphNode(p, v);
        nodemap.put(p, moo);
        String[] words = v.split("\\s+");
        for (String word : words) {
            if (traversallist.containsKey(word)) {
                traversallist.get(word).add(moo);
            }
            traversallist.computeIfAbsent(word, k -> new HashSet<>()).add(moo);

        }

    }

    public void addConnection(int from, int to) {
        GraphNode moo = findNode(from);
        GraphNode poo = findNode(to);
        if (moo != null && poo != null) {
            moo.neighbors.add(poo);
        }
    }
    public GraphNode findNode(int key) {
        return nodemap.get(key);
    }

    public List<String> traversal(String poo) {
        ArrayList<String> result = new ArrayList<>();
        Set<GraphNode> visited = new HashSet<>();
        result.add(poo);
        Set<GraphNode> startingNodes = traversallist.get(poo);
        if (startingNodes == null) {
            return null;
        }
        for (GraphNode node : startingNodes) {
            dfs(node, visited, result);
        }
        return result;
    }
    public void dfs(GraphNode node, Set<GraphNode> visited, List<String> result) {
        visited.add(node);
        result.add(node.value);
        for (GraphNode moo : node.neighbors) {
            if (!visited.contains(moo)) {
                dfs(moo, visited, result);
            }
        }
    }
    public String results(List<String> moo) {
        if (moo == null || moo.isEmpty()) {
            return "";
        }

        Set<String> uniqueWords = new HashSet<>();
        for (String poo : moo) {
            String[] shmoo = poo.split(" ");
            uniqueWords.addAll(Arrays.asList(shmoo));
        }

        List<String> sortedWords = new ArrayList<>(uniqueWords);
        Collections.sort(sortedWords);

        return sortedWords.toString();
    }


}
