package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P18_4Sum {

	public static void main(String[] args) {
		int[] nums = { 1, 0, -1, 0, -2, 2 };
		int target = 0;
		List<List<Integer>> results = fourSum(nums, target);
		results.forEach(a -> {
			a.forEach(b -> {
				System.out.print(b + " ");
			});
			System.out.println();
		});

	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> results = new ArrayList<>();
		if (nums == null || nums.length < 4) {
			return results;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 3; i++) {
			if (i != 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			for (int j = i + 1; j < nums.length - 2; j++) {
				if (j != i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				int left = j + 1;
				int right = nums.length - 1;
				while (left < right) {
					int sum = nums[i] + nums[j] + nums[left] + nums[right];
					if (sum < target) {
						left++;
					} else if (sum > target) {
						right--;
					} else {
						List<Integer> quadraplet = new ArrayList<>();
						quadraplet.add(nums[i]);
						quadraplet.add(nums[j]);
						quadraplet.add(nums[left]);
						quadraplet.add(nums[right]);
						results.add(quadraplet);
						left++;
						right--;

						while (left < right && nums[left] == nums[left - 1]) {
							left++;
						}
						while (left < right && nums[right] == nums[right + 1]) {
							right--;
						}
					}
				}
			}
		}
		return results;
	}

}
