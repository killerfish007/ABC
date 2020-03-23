package leetcode;

import java.util.ArrayList;
import java.util.List;

public class P39_CombinationSum {

	public static void main(String[] args) {

		int[] nums = { 2, 3, 6, 7 };
		List<List<Integer>> results = combinationSum(nums, 7);
		results.forEach(a -> {
			a.forEach(b -> {
				System.out.print(b + " ");
			});
			System.out.println();
		});

	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<>();
		List<Integer> tempList = new ArrayList<>();
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
				tempList.add(candidates[i]);
				backtrack(candidates, target - candidates[i], tempList, results, i);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

}
