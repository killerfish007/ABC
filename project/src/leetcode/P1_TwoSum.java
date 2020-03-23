package leetcode;

import java.util.HashMap;
import java.util.Map;

public class P1_TwoSum {
	public static void main(String[] args) {
		int[] nums = { 2, 7, 11, 15 };
		int target = 9;
		int[] re = twoSum(nums, target);
		System.out.println(re[0] + " " + re[1]);
	}

	public static int[] twoSum(int[] nums, int target) {
		int[] a = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			int comp = target - num;
			if (map.containsKey(num)) {
				a[0] = map.get(num);
				a[1] = i;
				break;
			} else {
				map.put(comp, i);
			}
		}
		return a;
	}

}
