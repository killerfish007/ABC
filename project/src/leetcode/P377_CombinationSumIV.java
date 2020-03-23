package leetcode;

public class P377_CombinationSumIV {

	public static void main(String[] args) {
		int[] nums = { 3, 33, 333 };
		int target = 10000;
		System.out.println(combinationSum4(nums, target));
	}

	public static int count = 0;

	public static int combinationSum4(int[] nums, int target) {
		backtrack(nums, target);
		return count;
	}

	private static void backtrack(int[] nums, int target) {
		if (target < 0) {
			return;
		} else if (target == 0) {
			count++;
		} else {
			for (int i = 0; i < nums.length; i++) {
				backtrack(nums, target - nums[i]);
			}
		}
	}

}
