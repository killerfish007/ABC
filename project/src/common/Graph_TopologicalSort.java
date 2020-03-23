package common;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Graph_TopologicalSort {
	public static void main(String[] args) {
		Graph<Integer> graph = new Graph<>(true);
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(3, 4);
		graph.addEdge(5, 6);
		graph.addEdge(6, 3);
		graph.addEdge(3, 8);
		graph.addEdge(8, 11);

		Stack<Vertex<Integer>> result = topSort(graph);
		while (!result.isEmpty()) {
			System.out.println(result.pop());
		}
	}

	public static Stack<Vertex<Integer>> topSort(Graph<Integer> graph) {
		Stack<Vertex<Integer>> stack = new Stack<>();
		Set<Vertex<Integer>> visited = new HashSet<>();
		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			if (visited.contains(vertex)) {
				continue;
			}
			topSortUtil(vertex, stack, visited);
		}
		return stack;
	}

	private static void topSortUtil(Vertex<Integer> vertex, Stack<Vertex<Integer>> stack,
			Set<Vertex<Integer>> visited) {
		visited.add(vertex);
		for (Vertex<Integer> childVertex : vertex.getAdjacentVertexes()) {
			if (visited.contains(childVertex)) {
				continue;
			}
			topSortUtil(childVertex, stack, visited);
		}
		stack.add(vertex);
	}
}
