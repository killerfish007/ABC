package leetcode;

import java.util.ArrayList;
import java.util.List;

public class P46_Permutations {

	public static void main(String[] args) {

		int[] nums = { 1, 2, 3 };
		List<List<Integer>> results = permute(nums);
		results.forEach(a -> {
			a.forEach(b -> {
				System.out.print(b + " ");
			});
			System.out.println();
		});

	}

	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return results;
		}
		List<Integer> tempList = new ArrayList<>();
		backtrack(nums, results, tempList);

		return results;
	}

	private static void backtrack(int[] nums, List<List<Integer>> results, List<Integer> tempList) {
		if (tempList.size() == nums.length) {
			results.add(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (tempList.contains(nums[i])) {
					continue;
				}
				tempList.add(nums[i]);
				backtrack(nums, results, tempList);
				tempList.remove(tempList.size() - 1);
			}
		}
	}
}
