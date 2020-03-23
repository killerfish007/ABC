package leetcode;

import java.util.ArrayList;
import java.util.List;

public class P216_CombinationSumIII {

	public static void main(String[] args) {
		List<List<Integer>> results = combinationSum3(3, 9);
		results.forEach(a -> {
			a.forEach(b -> {
				System.out.print(b + " ");
			});
			System.out.println();
		});
	}

	public static List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> results = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		backtrack(k, n, results, tempList, 1);
		return results;
	}

	private static void backtrack(int k, int n, List<List<Integer>> results, List<Integer> tempList, int startIndex) {
		if (n < 0) {
			return;
		} else if (n == 0 && tempList.size() == k) {
			results.add(new ArrayList<>(tempList));
		} else {
			for (int i = startIndex; i <= 9; i++) {
				tempList.add(i);
				backtrack(k, n - i, results, tempList, i + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

}
