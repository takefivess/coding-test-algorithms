import java.util.*;

/**
 * 그래프 탐색 관련 알고리즘 (BFS, DFS, 다익스트라 등)
 */
public class Graph {

    // 그래프를 인접 리스트로 표현
    static class GraphNode {
        int val;
        List<GraphNode> neighbors;
        
        GraphNode(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    // 1. 깊이 우선 탐색 (DFS - Recursion)
    public static void dfs(GraphNode node, Set<Integer> visited) {
        if (node == null || visited.contains(node.val)) return;
        
        visited.add(node.val);
        System.out.print(node.val + " ");
        
        for (GraphNode neighbor : node.neighbors) {
            dfs(neighbor, visited);
        }
    }

    // 2. 깊이 우선 탐색 (DFS - Stack)
    public static void dfsIterative(GraphNode start) {
        if (start == null) return;
        
        Set<Integer> visited = new HashSet<>();
        Stack<GraphNode> stack = new Stack<>();
        stack.push(start);
        
        while (!stack.isEmpty()) {
            GraphNode node = stack.pop();
            if (!visited.contains(node.val)) {
                visited.add(node.val);
                System.out.print(node.val + " ");
                
                for (GraphNode neighbor : node.neighbors) {
                    if (!visited.contains(neighbor.val)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    // 3. 너비 우선 탐색 (BFS - Queue)
    public static void bfs(GraphNode start) {
        if (start == null) return;
        
        Set<Integer> visited = new HashSet<>();
        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start.val);
        
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            System.out.print(node.val + " ");
            
            for (GraphNode neighbor : node.neighbors) {
                if (!visited.contains(neighbor.val)) {
                    visited.add(neighbor.val);
                    queue.add(neighbor);
                }
            }
        }
    }

    // 4. 위상 정렬 (Topological Sort)
    public static List<Integer> topologicalSort(int numNodes, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<>());
        }
        
        int[] indegree = new int[numNodes];
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            indegree[edge[1]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numNodes; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            
            for (int neighbor : graph.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        return result.size() == numNodes ? result : new ArrayList<>();
    }

    // 5. 연결 요소 찾기 (Connected Components)
    public static int countConnectedComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> visited = new HashSet<>();
        int components = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                components++;
                dfsComponent(i, graph, visited);
            }
        }
        
        return components;
    }

    private static void dfsComponent(int node, List<List<Integer>> graph, Set<Integer> visited) {
        visited.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfsComponent(neighbor, graph, visited);
            }
        }
    }

    // 6. 사이클 감지 (Undirected Graph)
    public static boolean hasCycle(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                if (dfsCycle(i, -1, graph, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    private static boolean dfsCycle(int node, int parent, List<List<Integer>> graph, Set<Integer> visited) {
        visited.add(node);
        
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                if (dfsCycle(neighbor, node, graph, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        
        return false;
    }

    // 7. 경로 찾기
    public static boolean pathExists(int n, int[][] edges, int source, int destination) {
        if (source == destination) return true;
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> visited = new HashSet<>();
        return dfsPath(source, destination, graph, visited);
    }

    private static boolean dfsPath(int node, int target, List<List<Integer>> graph, Set<Integer> visited) {
        if (node == target) return true;
        
        visited.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                if (dfsPath(neighbor, target, graph, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }

    // ==================== 테스트 코드 ====================
    public static void main(String[] args) {
        System.out.println("===== 그래프 알고리즘 테스트 =====\n");

        // 테스트용 그래프 생성
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        
        node1.neighbors.add(node2);
        node1.neighbors.add(node3);
        node2.neighbors.add(node4);
        node3.neighbors.add(node4);

        // 1. DFS (Recursive)
        System.out.println("1. DFS (Recursive):");
        dfs(node1, new HashSet<>());
        System.out.println("\n");

        // 2. DFS (Iterative)
        System.out.println("2. DFS (Iterative):");
        dfsIterative(node1);
        System.out.println("\n");

        // 3. BFS
        System.out.println("3. BFS:");
        bfs(node1);
        System.out.println("\n");

        // 4. 위상 정렬
        System.out.println("4. 위상 정렬:");
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}};
        System.out.println("결과: " + topologicalSort(3, edges));
        System.out.println();

        // 5. 연결 요소
        System.out.println("5. 연결 요소 개수:");
        int[][] edges2 = {{0, 1}, {2, 3}};
        System.out.println("결과: " + countConnectedComponents(4, edges2));
        System.out.println();

        // 6. 사이클 감지
        System.out.println("6. 사이클 감지:");
        int[][] edges3 = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("사이클 있음: " + hasCycle(3, edges3));
        System.out.println();

        // 7. 경로 찾기
        System.out.println("7. 경로 찾기:");
        int[][] edges4 = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("0에서 2로 경로 있음: " + pathExists(3, edges4, 0, 2));
    }
}
