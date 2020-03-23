package common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Graph_MinimumSpanningTree_Krushkal {

	
	
	
	
	
	
	
	public static void main(String[] args) {
		int A = 9;
		ArrayList<ArrayList<Integer>> B = new ArrayList<>(List.of(
				new ArrayList<>(List.of(0,1,4)),
				new ArrayList<>(List.of(0,7,8)),
				new ArrayList<>(List.of(1,2,8)),
				new ArrayList<>(List.of(1,7,11)),
				new ArrayList<>(List.of(2,3,7)),
				new ArrayList<>(List.of(2,5,4)),
				new ArrayList<>(List.of(3,4,9)),
				new ArrayList<>(List.of(5,4,10)),
				new ArrayList<>(List.of(6,5,2)),
				new ArrayList<>(List.of(7,6,1)),
				new ArrayList<>(List.of(7,8,7)),
				new ArrayList<>(List.of(8,2,2)),
				new ArrayList<>(List.of(8,6,6))));

		System.out.println(krushkal(A, B));
	}

	public static int krushkal(int A, ArrayList<ArrayList<Integer>> B) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		for (int i = 0; i < B.size(); i++) {
			pq.add(new int[] { B.get(i).get(0), B.get(i).get(1), B.get(i).get(2) });
		}

		int[] parent = new int[A];
		for (int i = 0; i < A; i++) {
			parent[i] = i;
		}
		int res = 0;
		int edgeCount = 0;
		while (edgeCount != A - 1) {
			int[] e = pq.poll();
			int parent1 = find(parent, e[0]);
			int parent2 = find(parent, e[1]);
			if (parent1 != parent2) {
				res += e[2];
				edgeCount++;
				parent = union(parent, parent1, parent2);
			}
		}
		return res;
	}

	public static int find(int[] parent, int ind) {
		while (parent[ind] != ind) {
			ind = parent[ind];
		}
		return ind;
	}

	public static int[] union(int[] parent, int parent1, int parent2) {
		parent[parent2] = parent1;
		return parent;
	}
}
