package leetcode;

public class P108_ConvertSortedArraytoBinarySearchTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0) {
			return null;
		}
		return helper(nums, 0, nums.length - 1);
	}

	public TreeNode helper(int[] nums, int low, int high) {
		if (low > high) {
			return null;
		}
		int mid = (low + high) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = helper(nums, low, mid - 1);
		root.right = helper(nums, mid + 1, high);
		return root;
	}

}
