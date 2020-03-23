package leetcode;

import java.util.ArrayList;
import java.util.List;

public class P78_Subsets {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3,4,5 };
		List<List<Integer>> results = subsets(nums);
		results.forEach(a -> {
			a.forEach(b -> {
				System.out.print(b + " ");
			});
			System.out.println();
		});

	}

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return results;
		}
		List<Integer> tempList = new ArrayList<>();
		backtrack(nums, results, tempList, 0);
		return results;
	}

	private static void backtrack(int[] nums, List<List<Integer>> results, List<Integer> tempList, int startIndex) {
		results.add(new ArrayList(tempList));
		for (int i = startIndex; i < nums.length; i++) {
			tempList.add(nums[i]);
			backtrack(nums, results, tempList, i + 1);
			tempList.remove(tempList.size() - 1);
		}

	}
}
