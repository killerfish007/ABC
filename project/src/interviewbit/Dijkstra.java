package interviewbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
	public static void main(String[] args) {
		ArrayList<String> A = new ArrayList<>();
		A.add("FEDCBECD");
		A.add("FABBGACG");
		A.add("CDEDGAEC");
		A.add("BFFEGGBA");
		A.add("FCEEAFDA");
		A.add("AGFADEAC");
		A.add("ADGDCBAA");
		A.add("EAABDDFF");

		String B = "BCDCB";
		System.out.println(exist(A, B));
		
		int a = 4;
		ArrayList<ArrayList<Integer>> b = new ArrayList<>();
		ArrayList<Integer> b1 = new ArrayList<>();
		b1.add(1);
		b1.add(2);
		b1.add(1);
		b.add(b1);
		ArrayList<Integer> b2 = new ArrayList<>();
		b2.add(2);
		b2.add(3);
		b2.add(2);
		b.add(b2);
		ArrayList<Integer> b3 = new ArrayList<>();
		b3.add(3);
		b3.add(4);
		b3.add(4);
		b.add(b3);
		ArrayList<Integer> b4 = new ArrayList<>();
		b4.add(1);
		b4.add(4);
		b4.add(3);
		b.add(b4);
		
		System.out.println(krushkal(a, b));
	}

	public static int exist(ArrayList<String> A, String B) {
		int row = A.size();
		int col = A.get(0).length();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (B.charAt(0) == A.get(i).charAt(j) && dfs(A, B, i, j, 1)) {
					return 1;
				}
			}
		}
		return 0;
	}

	public static boolean dfs(ArrayList<String> A, String B, int row, int col, int index) {
		if (index == B.length()) {
			return true;
		}

		int[] rowNbr = { -1, 0, 1, 0 };
		int[] colNbr = { 0, -1, 0, 1 };

		for (int i = 0; i < 4; i++) {
			int rowAdj = row + rowNbr[i];
			int colAdj = col + colNbr[i];
			if (rowAdj >= 0 && rowAdj < A.size() && colAdj >= 0 && colAdj < A.get(0).length()) {
				if (B.charAt(index) == A.get(rowAdj).charAt(colAdj)) {
					return dfs(A, B, rowAdj, colAdj, index + 1);
				}
			}
		}

		return false;
	}

	public static void solve(ArrayList<ArrayList<Character>> A) {
		int row = A.size();
		int col = A.get(0).size();

		for (int i = 0; i < col; i++) {
			if (A.get(0).get(i) == 'O') {
				dfs(A, 0, i);
			}
		}

		for (int i = 0; i < col; i++) {
			if (A.get(row - 1).get(i) == 'O') {
				dfs(A, row - 1, i);
			}
		}

		for (int i = 0; i < row; i++) {
			if (A.get(i).get(0) == 'O') {
				dfs(A, i, 0);
			}
		}

		for (int i = 0; i < row; i++) {
			if (A.get(i).get(col - 1) == 'O') {
				dfs(A, i, col - 1);
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				char c = A.get(i).get(j);
				if (c == 'O') {
					A.get(i).remove(j);
					A.get(i).add(j, 'X');
				} else if (c == '#') {
					A.get(i).remove(j);
					A.get(i).add(j, 'O');
				}
			}
		}

	}

	public static void dfs(ArrayList<ArrayList<Character>> A, int row, int col) {
		int[] rowNbr = { -1, 0, 1, 0 };
		int[] colNbr = { 0, -1, 0, 1 };
		A.get(row).remove(col);
		A.get(row).add(col, '#');

		for (int i = 0; i < 4; i++) {
			int adjrow = row + rowNbr[i];
			int adjcol = col + colNbr[i];
			if (adjrow >= 0 && adjrow < A.size() && adjcol >= 0 && adjcol < A.get(0).size()
					&& A.get(adjrow).get(adjcol) == 'O') {
				dfs(A, adjrow, adjcol);
			}
		}
	}

	public static int black(ArrayList<String> A) {
		int row = A.size();
		int col = A.get(0).length();
		boolean[][] visited = new boolean[row][col];
		int count = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (A.get(i).charAt(j) == 'X' && !visited[i][j]) {
					dfs(A, i, j, visited);
					count++;
				}
			}
		}
		return count;
	}

	public static void dfs(ArrayList<String> A, int row, int col, boolean[][] visited) {
		int[] rowNbr = { -1, 0, 1, 0 };
		int[] colNbr = { 0, 1, 0, -1 };
		visited[row][col] = true;

		for (int i = 0; i < 4; i++) {
			int adjrow = row + rowNbr[i];
			int adjcol = col + colNbr[i];
			if (adjrow >= 0 && adjrow < visited.length && adjcol >= 0 && adjcol < visited[0].length
					&& A.get(adjrow).charAt(adjcol) == 'X' && !visited[adjrow][adjcol]) {
				dfs(A, adjrow, adjcol, visited);
			}
		}
	}

	public static int solve(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
		int[] parent = new int[A + 1];
		for (int i = 0; i <= A; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < B.size(); i++) {
			int p1 = find(parent, B.get(i));
			int p2 = find(parent, C.get(i));
			if (p1 == p2) {
				return 0;
			} else {
				parent = union(parent, p1, p2);
			}
		}
		return 1;
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

		int[] parent = new int[A + 1];
		for (int i = 0; i <= A; i++) {
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

	public static ArrayList<Integer> dijkstra(int[][] graph, int source) {
		ArrayList<Integer> visitedVertex = new ArrayList<>();
		int n = graph.length;
		int[] distance = new int[n];
		for (int i = 0; i < n; i++) {
			distance[i] = -1;
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.distance - o2.distance;
			}
		});
		pq.add(new Edge(source, 0));
		distance[source] = 0;

		while (visitedVertex.size() != n) {
			Edge v = pq.poll();
			visitedVertex.add(v.index);
			int[] vAdj = graph[v.index];
			for (int i = 0; i < vAdj.length; i++) {
				if (vAdj[i] != 0) {
					int newD = distance[v.index] + vAdj[i];
					if (distance[i] == -1 || distance[i] > newD) {
						distance[i] = newD;
						pq.add(new Edge(i, newD));
					}
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

class Edge {
	int index;
	int distance;

	Edge(int index, int distance) {
		this.index = index;
		this.distance = distance;
	}
}
