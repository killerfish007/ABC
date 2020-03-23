package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P40_CombinationSumII {

	public static void main(String[] args) {

		int[] nums = { 2, 5, 2, 1, 2 };
		List<List<Integer>> results = combinationSum2(nums, 5);
		results.forEach(a -> {
			a.forEach(b -> {
				System.out.print(b + " ");
			});
			System.out.println();
		});

	}

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
		Arrays.sort(candidates);
		backtrack(candidates, target, tempList, results, 0);
		return results;
	}

	private static void backtrack(int[] candidates, int target, List<Integer> tempList, List<List<Integer>> results,
			int startIndex) {
		if (target == 0) {
			results.add(new ArrayList<>(tempList));
		} else if (target < 0) {
			return;
		} else {
			for (int i = startIndex; i < candidates.length; i++) {
				if (i > startIndex && candidates[i] == candidates[i - 1]) {
					continue;
				}
				tempList.add(candidates[i]);
				backtrack(candidates, target - candidates[i], tempList, results, i + 1);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
}
