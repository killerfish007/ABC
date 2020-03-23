package leetcode;

import java.util.ArrayList;
import java.util.List;

public class P77_Combinations {

	public static void main(String[] args) {
		List<List<Integer>> results = combine(4, 2);
		results.forEach(a -> {
			a.forEach(b -> {
				System.out.print(b + " ");
			});
			System.out.println();
		});
	}

	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> results = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		backtrack(n, k, results, tempList, 1);
		return results;
	}

	private static void backtrack(int n, int k, List<List<Integer>> results, List<Integer> tempList, int startIndex) {
		if (tempList.size() == k) {
			results.add(new ArrayList<>(tempList));
		} else {
			for (int i = startIndex; i <= n; i++) {
				tempList.add(i);
				backtrack(n, k, results, tempList, i + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

}
