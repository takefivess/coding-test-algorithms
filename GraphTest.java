import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@DisplayName("Graph 알고리즘 테스트")
public class GraphTest {

    private Graph graph;

    @BeforeEach
    void setUp() {
        graph = new Graph();
    }

    @Test
    @DisplayName("정점 추가 - 단일 정점")
    void testAddVertex() {
        graph.addVertex(1);
        assertTrue(graph.hasVertex(1));
    }

    @Test
    @DisplayName("정점 추가 - 여러 정점")
    void testAddMultipleVertices() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        
        assertTrue(graph.hasVertex(1));
        assertTrue(graph.hasVertex(2));
        assertTrue(graph.hasVertex(3));
    }

    @Test
    @DisplayName("간선 추가")
    void testAddEdge() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2);
        
        assertTrue(graph.hasEdge(1, 2));
    }

    @Test
    @DisplayName("인접 정점 조회")
    void testGetAdjacentVertices() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        
        List<Integer> adjacent = graph.getAdjacentVertices(1);
        assertEquals(2, adjacent.size());
        assertTrue(adjacent.contains(2));
        assertTrue(adjacent.contains(3));
    }

    @Test
    @DisplayName("BFS - 기본 케이스")
    void testBFS() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        
        List<Integer> result = graph.bfs(1);
        assertEquals(4, result.size());
        assertEquals(1, result.get(0));
    }

    @Test
    @DisplayName("BFS - 연결되지 않은 정점")
    void testBFSDisconnected() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        
        List<Integer> result = graph.bfs(1);
        assertEquals(2, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertFalse(result.contains(3));
    }

    @Test
    @DisplayName("DFS - 기본 케이스")
    void testDFS() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        
        List<Integer> result = graph.dfs(1);
        assertEquals(4, result.size());
        assertEquals(1, result.get(0));
    }

    @Test
    @DisplayName("DFS - 단일 정점")
    void testDFSSingleVertex() {
        graph.addVertex(1);
        
        List<Integer> result = graph.dfs(1);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0));
    }

    @Test
    @DisplayName("사이클 감지 - 사이클 있음")
    void testHasCycleTrue() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1); // Creates cycle
        
        assertTrue(graph.hasCycle());
    }

    @Test
    @DisplayName("사이클 감지 - 사이클 없음")
    void testHasCycleFalse() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        
        assertFalse(graph.hasCycle());
    }

    @Test
    @DisplayName("연결성 확인 - 연결됨")
    void testIsConnected() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        
        assertTrue(graph.isConnected(1, 3));
    }

    @Test
    @DisplayName("연결성 확인 - 연결 안 됨")
    void testIsNotConnected() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        
        assertFalse(graph.isConnected(1, 3));
    }

    @Test
    @DisplayName("최단 경로 - BFS 사용")
    void testShortestPath() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(1, 4);
        
        List<Integer> path = graph.shortestPath(1, 4);
        assertEquals(2, path.size());
        assertEquals(1, path.get(0));
        assertEquals(4, path.get(1));
    }

    @Test
    @DisplayName("정점 개수")
    void testVertexCount() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        
        assertEquals(3, graph.vertexCount());
    }

    @Test
    @DisplayName("간선 개수")
    void testEdgeCount() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        
        assertEquals(2, graph.edgeCount());
    }

    @Test
    @DisplayName("그래프 초기화 후 상태")
    void testEmptyGraph() {
        assertEquals(0, graph.vertexCount());
        assertEquals(0, graph.edgeCount());
    }
}
