package common;

import java.util.ArrayList;

public class Graph_ShortestPath_Dijkstra {
	
	
	
	
	
	
	public static void main(String[] args) {
		int graph[][] = new int[][] { 
			{ 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
			{ 4, 0, 8, 0, 0, 0, 0, 11, 0 },
			{ 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
			{ 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
			{ 0, 0, 0, 9, 0, 10, 0, 0, 0 },
			{ 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
			{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
			{ 8, 11, 0, 0, 0, 0, 1, 0, 7 },
			{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
		System.out.println(dijkstra(graph, 0));
	}
	public static ArrayList<Integer> dijkstra(int[][] graph, int source) {
		int noOfVertex = graph.length;
		int[] distance = new int[noOfVertex];
		boolean[] visitedVertex = new boolean[noOfVertex];
		for (int i = 0; i < noOfVertex; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[source] = 0;
		for (int count = 0; count < noOfVertex - 1; count++) {
			int min = Integer.MAX_VALUE, minIndex = -1;
			for (int v = 0; v < noOfVertex; v++) {
				if (visitedVertex[v] == false && distance[v] <= min) {
					min = distance[v];
					minIndex = v;
				}
			}
			visitedVertex[minIndex] = true;
			for (int v = 0; v < noOfVertex; v++) {
				if (!visitedVertex[v] && graph[minIndex][v] != 0 && distance[minIndex] != Integer.MAX_VALUE
						&& distance[minIndex] + graph[minIndex][v] < distance[v]) {
					distance[v] = distance[minIndex] + graph[minIndex][v];
				}
			}
		}
		ArrayList<Integer> res = new ArrayList<>();
		for (int a : distance) {
			res.add(a);
		}
		return res;
	}
}
