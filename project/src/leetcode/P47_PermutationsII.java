package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P47_PermutationsII {

	public static void main(String[] args) {

		int[] nums = { 1, 2, 2 };
		List<List<Integer>> results = permuteUnique(nums);
		results.forEach(a -> {
			a.forEach(b -> {
				System.out.print(b + " ");
			});
			System.out.println();
		});

	}

	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return results;
		}
		Arrays.sort(nums);
		List<Integer> tempList = new ArrayList<>();
		backtrack(nums, results, tempList, new boolean[nums.length]);

		return results;
	}

	private static void backtrack(int[] nums, List<List<Integer>> results, List<Integer> tempList, boolean[] used) {
		if (tempList.size() == nums.length) {
			results.add(new ArrayList<>(tempList));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
					continue;
				}
				used[i] = true;
				tempList.add(nums[i]);
				backtrack(nums, results, tempList, used);
				tempList.remove(tempList.size() - 1);
				used[i] = false;
			}
		}
	}

}
