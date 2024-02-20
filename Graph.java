package main;

import java.util.*;

public class Graph {
    public static class pair {
        private int key;
        private String value;

        public pair(int k, String v) {
            this.key = k;
            this.value = v;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            pair pair = (pair) o;
            return key == pair.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    private Map<pair, List<pair>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addWord(int k, String v) {
        pair id = new pair(k, v);
        adjacencyList.put(id, new ArrayList<>());
    }

    public void addConnection(pair id1, pair id2) {
        adjacencyList.get(id1).add(id2);
    }

    public List<pair> getSynonyms(pair id) {
        return adjacencyList.get(id);
    }

    public pair getPairByKey(int key) {
        for (pair pair : adjacencyList.keySet()) {
            if (pair.getKey() == (key)) {
                return pair;
            }
        }
        return null;
    }
    public int getkeybyvalue(String value) {
        for (pair pair : adjacencyList.keySet()) {
            if (Objects.equals(pair.getValue(), value)) {
                return pair.getKey();
            }
        }
        return -1;
    }
    public String dfs(int startNode) {
        TreeSet<pair> visitedNodes = new TreeSet<>(Comparator.comparing(pair::getValue));
        boolean[] visited = new boolean[adjacencyList.size()];
        dfsHelper(getPairByKey(startNode), visited, visitedNodes);
        for (pair node : visitedNodes) {
            System.out.print(node.getValue() + ", ");
        }
        return visitedNodes.toString();
    }

    private void dfsHelper(pair node, boolean[] visited, Set<pair> visitedNodes) {
        if (node == null || visited[node.getKey()]) {
            return;
        }

        visitedNodes.add(node);
        visited[node.getKey()] = true;

        for (pair neighbor : adjacencyList.get(node)) {
            dfsHelper(neighbor, visited, visitedNodes);
        }
    }

}



